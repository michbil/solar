/*   1:    */ package cn.com.voltronic.solar.exception;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.configure.ConfigureTools;
/*   4:    */ import cn.com.voltronic.solar.configure.GlobalConfig;
/*   5:    */ import cn.com.voltronic.solar.dao.EventCfgDao;
/*   6:    */ import cn.com.voltronic.solar.dao.EventRecordDao;
/*   7:    */ import cn.com.voltronic.solar.data.bean.EventData;
/*   8:    */ import cn.com.voltronic.solar.data.bean.EventDataRecord;
/*   9:    */ import cn.com.voltronic.solar.data.bean.Eventcfg;
/*  10:    */ import cn.com.voltronic.solar.socket.UdpClient;
/*  11:    */ import cn.com.voltronic.solar.system.GlobalVariables;
/*  12:    */ import cn.com.voltronic.solar.thread.SendEmailThread;
/*  13:    */ import cn.com.voltronic.solar.thread.SendSMSThread;
/*  14:    */ import cn.com.voltronic.solar.util.DateUtils;
/*  15:    */ import cn.com.voltronic.solar.util.VolUtil;
/*  16:    */ import java.io.File;
/*  17:    */ import java.io.FileInputStream;
/*  18:    */ import java.util.Date;
/*  19:    */ import java.util.List;
/*  20:    */ import org.dom4j.Document;
/*  21:    */ import org.dom4j.Element;
/*  22:    */ import org.dom4j.io.SAXReader;
/*  23:    */ 
/*  24:    */ public class EventsHandler
/*  25:    */ {
/*  26: 35 */   private static Document document30 = null;
/*  27: 36 */   private static final String XML_PATH = System.getProperty("user.dir") + 
/*  28: 37 */     File.separator + "config" + File.separator;
/*  29:    */   
/*  30:    */   static
/*  31:    */   {
/*  32:    */     try
/*  33:    */     {
/*  34: 41 */       document30 = doDocument("Events_P30.xml");
/*  35:    */     }
/*  36:    */     catch (Exception e)
/*  37:    */     {
/*  38: 43 */       e.printStackTrace();
/*  39:    */     }
/*  40:    */   }
/*  41:    */   
/*  42:    */   public static Document getDocument30()
/*  43:    */   {
/*  44: 48 */     return document30;
/*  45:    */   }
/*  46:    */   
/*  47:    */   public static Document getDocumentByProdid(String prodid)
/*  48:    */   {
/*  49: 52 */     if (prodid.equals("P30")) {
/*  50: 53 */       return document30;
/*  51:    */     }
/*  52: 55 */     return document30;
/*  53:    */   }
/*  54:    */   
/*  55:    */   public static List<Element> getP30AllEvent()
/*  56:    */   {
/*  57: 61 */     List<Element> list = document30.getRootElement().elements("event");
/*  58: 62 */     return list;
/*  59:    */   }
/*  60:    */   
/*  61:    */   private static Document doDocument(String fileName)
/*  62:    */   {
/*  63: 66 */     Document document = null;
/*  64: 67 */     SAXReader reader = new SAXReader();
/*  65: 68 */     File fileExt = null;
/*  66:    */     try
/*  67:    */     {
/*  68: 70 */       fileExt = new File(XML_PATH + fileName);
/*  69:    */     }
/*  70:    */     catch (Exception localException1) {}
/*  71: 73 */     if ((fileExt != null) && (fileExt.exists()) && (fileExt.isFile()))
/*  72:    */     {
/*  73: 74 */       FileInputStream fisExt = null;
/*  74:    */       try
/*  75:    */       {
/*  76: 76 */         fisExt = new FileInputStream(fileExt);
/*  77: 77 */         document = reader.read(fisExt);
/*  78:    */       }
/*  79:    */       catch (Exception e)
/*  80:    */       {
/*  81: 79 */         e.printStackTrace();
/*  82: 81 */         if (fisExt != null) {
/*  83:    */           try
/*  84:    */           {
/*  85: 83 */             fisExt.close();
/*  86:    */           }
/*  87:    */           catch (Exception localException2) {}
/*  88:    */         }
/*  89:    */       }
/*  90:    */       finally
/*  91:    */       {
/*  92: 81 */         if (fisExt != null) {
/*  93:    */           try
/*  94:    */           {
/*  95: 83 */             fisExt.close();
/*  96:    */           }
/*  97:    */           catch (Exception localException3) {}
/*  98:    */         }
/*  99:    */       }
/* 100:    */     }
/* 101: 89 */     return document;
/* 102:    */   }
/* 103:    */   
/* 104:    */   public static EventData getEventById(String prodid, String eventId)
/* 105:    */   {
/* 106: 93 */     String eventName = "";
/* 107: 94 */     int eventLevel = 0;
/* 108: 95 */     if (prodid.equalsIgnoreCase("P30"))
/* 109:    */     {
/* 110: 96 */       eventName = getEventname(document30, eventId);
/* 111: 97 */       eventLevel = getEventLevel(document30, eventId);
/* 112:    */     }
/* 113:    */     else
/* 114:    */     {
/* 115: 99 */       eventName = getEventname(document30, eventId);
/* 116:100 */       eventLevel = getEventLevel(document30, eventId);
/* 117:    */     }
/* 118:102 */     EventData eventData = new EventData();
/* 119:103 */     eventData.setEventId(eventId);
/* 120:104 */     eventData.setEventLevel(eventLevel);
/* 121:105 */     eventData.setEventName(eventName);
/* 122:106 */     return eventData;
/* 123:    */   }
/* 124:    */   
/* 125:    */   public static int getEventLevel(Document document, String eventId)
/* 126:    */   {
/* 127:110 */     Element eventEle = (Element)document.selectSingleNode("//event[@id='" + 
/* 128:111 */       eventId + "']");
/* 129:112 */     String level = eventEle.attributeValue("level");
/* 130:113 */     return Integer.parseInt(level);
/* 131:    */   }
/* 132:    */   
/* 133:    */   public static String getEventname(Document document, String eventId)
/* 134:    */   {
/* 135:117 */     Element eventEle = (Element)document.selectSingleNode("//event[@id='" + 
/* 136:118 */       eventId + "']");
/* 137:119 */     String language = "en_US";
/* 138:    */     try
/* 139:    */     {
/* 140:121 */       ConfigureTools.wrapProperties(GlobalVariables.globalConfig);
/* 141:122 */       language = GlobalVariables.globalConfig.getLanguage();
/* 142:    */     }
/* 143:    */     catch (Exception ex)
/* 144:    */     {
/* 145:124 */       ex.printStackTrace();
/* 146:    */     }
/* 147:126 */     String name = null;
/* 148:127 */     if (language.equals("en_US")) {
/* 149:128 */       name = eventEle.attributeValue("enName");
/* 150:129 */     } else if (language.equals("zh_CN")) {
/* 151:130 */       name = eventEle.attributeValue("name");
/* 152:131 */     } else if (language.equals("fr_FR")) {
/* 153:132 */       name = eventEle.attributeValue("frName");
/* 154:133 */     } else if (language.equals("de_DE")) {
/* 155:134 */       name = eventEle.attributeValue("deName");
/* 156:135 */     } else if (language.equals("it_IT")) {
/* 157:136 */       name = eventEle.attributeValue("itName");
/* 158:137 */     } else if (language.equals("pl_PL")) {
/* 159:138 */       name = eventEle.attributeValue("plName");
/* 160:139 */     } else if (language.equals("pt_PT")) {
/* 161:140 */       name = eventEle.attributeValue("ptName");
/* 162:141 */     } else if (language.equals("ru_RU")) {
/* 163:142 */       name = eventEle.attributeValue("rusName");
/* 164:143 */     } else if (language.equals("es_ES")) {
/* 165:144 */       name = eventEle.attributeValue("spName");
/* 166:145 */     } else if (language.equals("uk_UA")) {
/* 167:146 */       name = eventEle.attributeValue("ukrName");
/* 168:147 */     } else if (language.equals("tr_TR")) {
/* 169:148 */       name = eventEle.attributeValue("turName");
/* 170:149 */     } else if (language.equals("zh_TW")) {
/* 171:150 */       name = eventEle.attributeValue("twName");
/* 172:    */     } else {
/* 173:152 */       name = eventEle.attributeValue("enName");
/* 174:    */     }
/* 175:154 */     return name;
/* 176:    */   }
/* 177:    */   
/* 178:    */   public static void handleEvent(String prodid, String serialno, Date trandate, String eventId)
/* 179:    */   {
/* 180:159 */     if ((prodid != null) && (!"".equals(prodid)) && 
/* 181:160 */       (serialno != null) && (!"".equals(serialno)) && 
/* 182:161 */       (eventId != null) && (!"".equals(eventId)) && 
/* 183:162 */       (trandate != null)) {
/* 184:163 */       reocrdEvent(prodid, serialno, eventId, trandate);
/* 185:    */     }
/* 186:    */   }
/* 187:    */   
/* 188:    */   private static void reocrdEvent(String prodid, String serialno, String eventId, Date trandate)
/* 189:    */   {
/* 190:169 */     EventCfgDao cfgDao = new EventCfgDao();
/* 191:170 */     List<Eventcfg> cfgList = cfgDao.queryEventcfg(eventId);
/* 192:171 */     for (int i = 0; i < cfgList.size(); i++)
/* 193:    */     {
/* 194:172 */       Eventcfg cfg = (Eventcfg)cfgList.get(i);
/* 195:173 */       if (cfg.getAction() == 1)
/* 196:    */       {
/* 197:175 */         EventRecordDao eventDao = new EventRecordDao();
/* 198:176 */         EventDataRecord eventdata = new EventDataRecord(prodid, 
/* 199:177 */           serialno, eventId, trandate);
/* 200:178 */         if (!eventDao.alreadyInsert(eventdata)) {
/* 201:179 */           eventDao.insertEvent(eventdata);
/* 202:    */         }
/* 203:    */       }
/* 204:182 */       if (cfg.getAction() == 2)
/* 205:    */       {
/* 206:184 */         String port = "38694";
/* 207:    */         try
/* 208:    */         {
/* 209:186 */           port = GlobalVariables.globalConfig.getUdpPort();
/* 210:    */         }
/* 211:    */         catch (Exception ex)
/* 212:    */         {
/* 213:188 */           ex.printStackTrace();
/* 214:    */         }
/* 215:190 */         EventData eventData = getEventById(prodid, eventId);
/* 216:191 */         String message = eventData.getEventName();
/* 217:192 */         UdpClient udpClient = null;
/* 218:    */         try
/* 219:    */         {
/* 220:194 */           udpClient = new UdpClient("localhost", port);
/* 221:195 */           String eventDescription = "(Event:[" + serialno + "] [" + 
/* 222:196 */             VolUtil.getFormatTimestamp(trandate) + "] " + 
/* 223:197 */             message + "13";
/* 224:198 */           udpClient.send(eventDescription);
/* 225:    */         }
/* 226:    */         catch (Exception e)
/* 227:    */         {
/* 228:200 */           e.printStackTrace();
/* 229:    */         }
/* 230:    */         finally
/* 231:    */         {
/* 232:202 */           if (udpClient != null) {
/* 233:203 */             udpClient.disconnect();
/* 234:    */           }
/* 235:    */         }
/* 236:    */       }
/* 237:207 */       if (cfg.getAction() == 3)
/* 238:    */       {
/* 239:209 */         EventData eventData = getEventById(prodid, eventId);
/* 240:210 */         String message = eventData.getEventName();
/* 241:211 */         SendEmailThread sendEmailUtil = new SendEmailThread(serialno, 
/* 242:212 */           DateUtils.getFormatTimestamp(trandate), message, 
/* 243:213 */           cfg.getReceive());
/* 244:214 */         sendEmailUtil.start();
/* 245:    */       }
/* 246:216 */       if (cfg.getAction() == 4)
/* 247:    */       {
/* 248:218 */         EventData eventData = getEventById(prodid, eventId);
/* 249:219 */         String message = eventData.getEventName();
/* 250:220 */         SendSMSThread sendSMSUtil = new SendSMSThread(serialno, 
/* 251:221 */           DateUtils.getFormatTimestamp(trandate), message, 
/* 252:222 */           cfg.getReceive());
/* 253:223 */         sendSMSUtil.start();
/* 254:    */       }
/* 255:    */     }
/* 256:    */   }
/* 257:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.exception.EventsHandler
 * JD-Core Version:    0.7.0.1
 */