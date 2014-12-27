/*   1:    */ package cn.com.voltronic.solar.configure;
/*   2:    */ 
/*   3:    */ import java.io.BufferedInputStream;
/*   4:    */ import java.io.FileInputStream;
/*   5:    */ import java.io.FileOutputStream;
/*   6:    */ import java.io.IOException;
/*   7:    */ import java.io.InputStream;
/*   8:    */ import java.util.Enumeration;
/*   9:    */ import java.util.HashMap;
/*  10:    */ import java.util.Iterator;
/*  11:    */ import java.util.Map;
/*  12:    */ import java.util.Properties;
/*  13:    */ import java.util.Set;
/*  14:    */ 
/*  15:    */ public class IniProperties
/*  16:    */ {
/*  17:    */   private Properties globalProperties;
/*  18:    */   private String propfile;
/*  19:    */   private Map<String, Properties> properties;
/*  20:    */   
/*  21:    */   static enum ParseState
/*  22:    */   {
/*  23: 23 */     NORMAL,  ESCAPE,  ESC_CRNL,  COMMENT;
/*  24:    */   }
/*  25:    */   
/*  26:    */   public IniProperties()
/*  27:    */   {
/*  28: 27 */     this.globalProperties = new Properties();
/*  29: 28 */     this.properties = new HashMap();
/*  30:    */   }
/*  31:    */   
/*  32:    */   public void setProperyfile(String filewithpath)
/*  33:    */   {
/*  34: 33 */     this.propfile = filewithpath;
/*  35:    */   }
/*  36:    */   
/*  37:    */   public void load()
/*  38:    */     throws Exception
/*  39:    */   {
/*  40: 37 */     this.globalProperties.clear();
/*  41: 38 */     this.properties.clear();
/*  42: 39 */     InputStream in = null;
/*  43: 40 */     FileInputStream stream = null;
/*  44:    */     try
/*  45:    */     {
/*  46: 42 */       stream = new FileInputStream(this.propfile);
/*  47: 43 */       if (stream != null)
/*  48:    */       {
/*  49: 44 */         in = new BufferedInputStream(stream);
/*  50: 45 */         load(in);
/*  51: 46 */         stream.close();
/*  52:    */       }
/*  53:    */     }
/*  54:    */     catch (Exception e)
/*  55:    */     {
/*  56: 50 */       throw e;
/*  57:    */     }
/*  58:    */     finally
/*  59:    */     {
/*  60: 52 */       if (in != null) {
/*  61: 53 */         stream.close();
/*  62:    */       }
/*  63: 55 */       if (in != null) {
/*  64: 56 */         in.close();
/*  65:    */       }
/*  66:    */     }
/*  67:    */   }
/*  68:    */   
/*  69:    */   private void load(InputStream in)
/*  70:    */     throws IOException
/*  71:    */   {
/*  72: 62 */     int bufSize = 4096;
/*  73: 63 */     byte[] buffer = new byte[bufSize];
/*  74: 64 */     int n = in.read(buffer, 0, bufSize);
/*  75:    */     
/*  76: 66 */     ParseState state = ParseState.NORMAL;
/*  77: 67 */     boolean section_open = false;
/*  78: 68 */     String current_section = null;
/*  79: 69 */     String key = null;String value = null;
/*  80: 70 */     StringBuilder sb = new StringBuilder();
/*  81: 71 */     while (n >= 0)
/*  82:    */     {
/*  83: 72 */       for (int i = 0; i < n; i++)
/*  84:    */       {
/*  85: 73 */         char c = (char)buffer[i];
/*  86: 75 */         if (state == ParseState.COMMENT)
/*  87:    */         {
/*  88: 77 */           if ((c == '\r') || (c == '\n')) {
/*  89: 78 */             state = ParseState.NORMAL;
/*  90:    */           }
/*  91:    */         }
/*  92: 84 */         else if (state == ParseState.ESCAPE)
/*  93:    */         {
/*  94: 85 */           sb.append(c);
/*  95: 86 */           if (c == '\r') {
/*  96: 88 */             state = ParseState.ESC_CRNL;
/*  97:    */           } else {
/*  98: 90 */             state = ParseState.NORMAL;
/*  99:    */           }
/* 100:    */         }
/* 101:    */         else
/* 102:    */         {
/* 103: 95 */           switch (c)
/* 104:    */           {
/* 105:    */           case '[': 
/* 106: 97 */             sb = new StringBuilder();
/* 107: 98 */             section_open = true;
/* 108: 99 */             break;
/* 109:    */           case ']': 
/* 110:102 */             if (section_open)
/* 111:    */             {
/* 112:103 */               current_section = sb.toString().trim();
/* 113:104 */               sb = new StringBuilder();
/* 114:105 */               this.properties.put(current_section, new Properties());
/* 115:106 */               section_open = false;
/* 116:    */             }
/* 117:    */             else
/* 118:    */             {
/* 119:108 */               sb.append(c);
/* 120:    */             }
/* 121:110 */             break;
/* 122:    */           case '\\': 
/* 123:113 */             state = ParseState.ESCAPE;
/* 124:114 */             break;
/* 125:    */           case '#': 
/* 126:    */           case ';': 
/* 127:118 */             state = ParseState.COMMENT;
/* 128:119 */             break;
/* 129:    */           case ':': 
/* 130:    */           case '=': 
/* 131:123 */             if (key == null)
/* 132:    */             {
/* 133:124 */               key = sb.toString().trim();
/* 134:125 */               sb = new StringBuilder();
/* 135:    */             }
/* 136:    */             else
/* 137:    */             {
/* 138:127 */               sb.append(c);
/* 139:    */             }
/* 140:129 */             break;
/* 141:    */           case '\n': 
/* 142:    */           case '\r': 
/* 143:133 */             if ((state == ParseState.ESC_CRNL) && (c == '\n'))
/* 144:    */             {
/* 145:134 */               sb.append(c);
/* 146:135 */               state = ParseState.NORMAL;
/* 147:    */             }
/* 148:    */             else
/* 149:    */             {
/* 150:137 */               if (sb.length() > 0)
/* 151:    */               {
/* 152:138 */                 value = sb.toString().trim();
/* 153:139 */                 sb = new StringBuilder();
/* 154:141 */                 if (key != null) {
/* 155:142 */                   if (current_section == null) {
/* 156:143 */                     setProperty(key, value);
/* 157:    */                   } else {
/* 158:145 */                     setProperty(current_section, key, 
/* 159:146 */                       value);
/* 160:    */                   }
/* 161:    */                 }
/* 162:    */               }
/* 163:150 */               key = null;
/* 164:151 */               value = null;
/* 165:    */             }
/* 166:153 */             break;
/* 167:    */           default: 
/* 168:156 */             sb.append(c);
/* 169:    */           }
/* 170:    */         }
/* 171:    */       }
/* 172:159 */       n = in.read(buffer, 0, bufSize);
/* 173:    */     }
/* 174:    */   }
/* 175:    */   
/* 176:    */   public String getProperty(String name)
/* 177:    */   {
/* 178:164 */     return this.globalProperties.getProperty(name);
/* 179:    */   }
/* 180:    */   
/* 181:    */   public void setProperty(String name, String value)
/* 182:    */   {
/* 183:168 */     this.globalProperties.setProperty(name, value);
/* 184:    */   }
/* 185:    */   
/* 186:    */   public Iterator<String> properties()
/* 187:    */   {
/* 188:173 */     return new IteratorFromEnumeration(
/* 189:174 */       this.globalProperties.propertyNames());
/* 190:    */   }
/* 191:    */   
/* 192:    */   public String getProperty(String section, String name)
/* 193:    */   {
/* 194:182 */     Properties p = (Properties)this.properties.get(section);
/* 195:183 */     return p == null ? null : p.getProperty(name);
/* 196:    */   }
/* 197:    */   
/* 198:    */   public void setProperty(String section, String name, String value)
/* 199:    */   {
/* 200:191 */     Properties p = (Properties)this.properties.get(section);
/* 201:192 */     if (p == null)
/* 202:    */     {
/* 203:193 */       p = new Properties();
/* 204:194 */       this.properties.put(section, p);
/* 205:    */     }
/* 206:196 */     p.setProperty(name, value);
/* 207:    */   }
/* 208:    */   
/* 209:    */   public Iterator<String> properties(String section)
/* 210:    */   {
/* 211:206 */     Properties p = (Properties)this.properties.get(section);
/* 212:207 */     if (p == null) {
/* 213:208 */       return null;
/* 214:    */     }
/* 215:210 */     return new IteratorFromEnumeration(
/* 216:211 */       p.propertyNames());
/* 217:    */   }
/* 218:    */   
/* 219:    */   public Iterator<String> sections()
/* 220:    */   {
/* 221:218 */     return this.properties.keySet().iterator();
/* 222:    */   }
/* 223:    */   
/* 224:    */   private static String dumpEscape(String s)
/* 225:    */   {
/* 226:226 */     return 
/* 227:227 */       s.replaceAll("\\\\", "\\\\\\\\").replaceAll(";", "\\\\;").replaceAll("#", "\\\\#").replaceAll("(\r?\n|\r)", "\\\\$1");
/* 228:    */   }
/* 229:    */   
/* 230:    */   private static class IteratorFromEnumeration<E>
/* 231:    */     implements Iterator
/* 232:    */   {
/* 233:    */     private Enumeration<E> e;
/* 234:    */     
/* 235:    */     public IteratorFromEnumeration(Enumeration<E> e)
/* 236:    */     {
/* 237:236 */       this.e = e;
/* 238:    */     }
/* 239:    */     
/* 240:    */     public boolean hasNext()
/* 241:    */     {
/* 242:240 */       return this.e.hasMoreElements();
/* 243:    */     }
/* 244:    */     
/* 245:    */     public E next()
/* 246:    */     {
/* 247:244 */       return this.e.nextElement();
/* 248:    */     }
/* 249:    */     
/* 250:    */     public void remove()
/* 251:    */     {
/* 252:248 */       throw new UnsupportedOperationException(
/* 253:249 */         "Can't change underlying enumeration");
/* 254:    */     }
/* 255:    */   }
/* 256:    */   
/* 257:    */   public void store()
/* 258:    */     throws IOException
/* 259:    */   {
/* 260:254 */     store(this.propfile);
/* 261:    */   }
/* 262:    */   
/* 263:    */   public void store(String filewithpath)
/* 264:    */     throws IOException
/* 265:    */   {
/* 266:258 */     FileOutputStream fileOut = null;
/* 267:    */     try
/* 268:    */     {
/* 269:260 */       fileOut = new FileOutputStream(filewithpath);
/* 270:261 */       store(fileOut);
/* 271:    */     }
/* 272:    */     finally
/* 273:    */     {
/* 274:263 */       if (fileOut != null) {
/* 275:264 */         fileOut.close();
/* 276:    */       }
/* 277:    */     }
/* 278:    */   }
/* 279:    */   
/* 280:    */   private void store(FileOutputStream out)
/* 281:    */     throws IOException
/* 282:    */   {
/* 283:272 */     Iterator<String> props = properties();
/* 284:273 */     while (props.hasNext())
/* 285:    */     {
/* 286:274 */       String name = (String)props.next();
/* 287:275 */       String format = String.format("%s = %s\r\n", new Object[] { name, 
/* 288:276 */         dumpEscape(getProperty(name)) });
/* 289:277 */       out.write(format.getBytes());
/* 290:    */     }
/* 291:280 */     Iterator<String> sections = sections();
/* 292:281 */     for (; sections.hasNext(); props.hasNext())
/* 293:    */     {
/* 294:282 */       String section = (String)sections.next();
/* 295:283 */       String format = String.format("\r\n[%s]\r\n", new Object[] { section });
/* 296:284 */       out.write(format.getBytes());
/* 297:285 */       props = properties(section);
/* 298:286 */       continue;
/* 299:287 */       String name = (String)props.next();
/* 300:    */       
/* 301:289 */       format = String.format("%s = %s\r\n", new Object[] { name, 
/* 302:290 */         dumpEscape(getProperty(section, name)) });
/* 303:291 */       out.write(format.getBytes());
/* 304:    */     }
/* 305:    */   }
/* 306:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.configure.IniProperties
 * JD-Core Version:    0.7.0.1
 */