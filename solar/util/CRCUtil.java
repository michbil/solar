/*   1:    */ package cn.com.voltronic.solar.util;
/*   2:    */ 
/*   3:    */ import java.io.ByteArrayOutputStream;
/*   4:    */ import java.io.PrintStream;
/*   5:    */ 
/*   6:    */ public class CRCUtil
/*   7:    */ {
/*   8: 13 */   private static char[] crc_tb = { '\000', 'အ', '⁂', 'っ', '䂄', 
/*   9: 14 */     '傥', '惆', '烧', 33032, 37161, 41290, 45419, 49548, 
/*  10: 15 */     53677, 57806, 61935, 'ሱ', 'Ȑ', '㉳', '≒', '劵', 
/*  11: 16 */     '䊔', '狷', '拖', 37689, 33560, 45947, 41818, 54205, 
/*  12: 17 */     50076, 62463, 58334, '③', '㑃', 'Р', 'ᐁ', '擦', 
/*  13: 18 */     '瓇', '䒤', '咅', 42346, 46411, 34088, 38153, 58862, 
/*  14: 19 */     62927, 50604, 54669, '㙓', '♲', 'ᘑ', 'ذ', '盗', 
/*  15: 20 */     '曶', '嚕', '䚴', 46939, 42874, 38681, 34616, 63455, 
/*  16: 21 */     59390, 55197, 51132, '䣄', '壥', '梆', '碧', 'ࡀ', 
/*  17: 22 */     'ᡡ', '⠂', '㠣', 51660, 55789, 59790, 63919, 35144, 
/*  18: 23 */     39273, 43274, 47403, '嫵', '䫔', '窷', '檖', 'ᩱ', 
/*  19: 24 */     '੐', '㨳', '⨒', 56317, 52188, 64447, 60318, 39801, 
/*  20: 25 */     35672, 47931, 43802, '沦', '粇', '䳤', '峅', 'Ⱒ', 
/*  21: 26 */     '㰃', 'ౠ', '᱁', 60846, 64911, 52716, 56781, 44330, 
/*  22: 27 */     48395, 36200, 40265, '纗', '溶', '廕', '仴', '㸓', 
/*  23: 28 */     '⸲', 'ṑ', '๰', 65439, 61374, 57309, 53244, 48923, 
/*  24: 29 */     44858, 40793, 36728, 37256, 33193, 45514, 41451, 53516, 
/*  25: 30 */     49453, 61774, 57711, 'ႀ', '¡', 'ヂ', '⃣', '倄', 
/*  26: 31 */     '䀥', '灆', '恧', 33721, 37784, 41979, 46042, 49981, 
/*  27: 32 */     54044, 58239, 62302, 'ʱ', 'ነ', '⋳', '㋒', '䈵', 
/*  28: 33 */     '刔', '扷', '牖', 46570, 42443, 38312, 34185, 62830, 
/*  29: 34 */     58703, 54572, 50445, '㓢', 'Ⓝ', 'ᒠ', 'ҁ', '瑦', 
/*  30: 35 */     '摇', '吤', '䐅', 42971, 47098, 34713, 38840, 59231, 
/*  31: 36 */     63358, 50973, 55100, '⛓', '㛲', 'ڑ', 'ᚰ', '晗', 
/*  32: 37 */     '癶', '䘕', '嘴', 55628, 51565, 63758, 59695, 39368, 
/*  33: 38 */     35305, 47498, 43435, '塄', '䡥', '砆', '栧', 'ᣀ', 
/*  34: 39 */     '࣡', '㢂', '⢣', 52093, 56156, 60223, 64286, 35833, 
/*  35: 40 */     39896, 43963, 48026, '䩵', '婔', '樷', '稖', '૱', 
/*  36: 41 */     '᫐', '⪳', '㪒', 64814, 60687, 56684, 52557, 48554, 
/*  37: 42 */     44427, 40424, 36297, '簦', '氇', '層', '䱅', '㲢', 
/*  38: 43 */     'ⲃ', '᳠', 'ು', 61215, 65342, 53085, 57212, 44955, 
/*  39: 44 */     49082, 36825, 40952, '渗', '縶', '乕', '年', '⺓', 
/*  40: 45 */     '㺲', '໑', 'Ự' };
/*  41:    */   
/*  42:    */   public static boolean checkCRC(String resultValue)
/*  43:    */   {
/*  44: 48 */     boolean result = false;
/*  45: 49 */     String firstValue = resultValue.substring(0, resultValue.length() - 2);
/*  46: 50 */     String lastValue = resultValue.substring(resultValue.length() - 2);
/*  47:    */     
/*  48:    */ 
/*  49: 53 */     byte[] pByte = firstValue.getBytes();
/*  50: 54 */     int returnV = caluCRC(pByte);
/*  51:    */     
/*  52: 56 */     String lastV = toHexString(lastValue);
/*  53: 57 */     int reV = Integer.parseInt(lastV, 16);
/*  54: 59 */     if (reV == returnV) {
/*  55: 60 */       result = true;
/*  56:    */     } else {
/*  57: 63 */       result = false;
/*  58:    */     }
/*  59: 66 */     return result;
/*  60:    */   }
/*  61:    */   
/*  62:    */   public static byte[] getCRCByte(String command)
/*  63:    */   {
/*  64: 71 */     int crcint = caluCRC(command.getBytes());
/*  65: 72 */     int crclow = crcint & 0xFF;
/*  66: 73 */     int crchigh = crcint >> 8 & 0xFF;
/*  67:    */     
/*  68: 75 */     return new byte[] { (byte)crchigh, (byte)crclow };
/*  69:    */   }
/*  70:    */   
/*  71:    */   public static String getCRC(String command)
/*  72:    */   {
/*  73:109 */     int crcint = caluCRC(command.getBytes());
/*  74:    */     
/*  75:111 */     int crclow = crcint & 0xFF;
/*  76:112 */     int crchigh = crcint >> 8 & 0xFF;
/*  77:    */     
/*  78:114 */     System.out.println(" command=" + command + "  crclow=" + crclow + "   crchigh=" + crchigh);
/*  79:    */     
/*  80:116 */     return new String(new byte[] { (byte)crchigh, (byte)crclow });
/*  81:    */   }
/*  82:    */   
/*  83:    */   public static String toHexString(String s)
/*  84:    */   {
/*  85:144 */     String str = "";
/*  86:145 */     for (int i = 0; i < s.length(); i++)
/*  87:    */     {
/*  88:146 */       short ch = (short)s.charAt(i);
/*  89:147 */       if (ch < 0) {
/*  90:148 */         ch = (short)(ch + 256);
/*  91:    */       }
/*  92:151 */       String s4 = Integer.toHexString(ch);
/*  93:152 */       if (s4.length() < 2) {
/*  94:153 */         s4 = "0" + s4;
/*  95:    */       }
/*  96:155 */       str = str + s4;
/*  97:    */     }
/*  98:158 */     return str;
/*  99:    */   }
/* 100:    */   
/* 101:    */   private static int caluCRC(byte[] pByte)
/* 102:    */   {
/* 103:    */     try
/* 104:    */     {
/* 105:163 */       int len = pByte.length;
/* 106:    */       
/* 107:    */ 
/* 108:    */ 
/* 109:    */ 
/* 110:168 */       int i = 0;
/* 111:169 */       int crc = 0;
/* 112:170 */       while (len-- != 0)
/* 113:    */       {
/* 114:171 */         int da = 0xFF & (0xFF & crc >> 8) >> 4;
/* 115:172 */         crc <<= 4;
/* 116:173 */         crc ^= crc_tb[(0xFF & (da ^ pByte[i] >> 4))];
/* 117:    */         
/* 118:175 */         da = 0xFF & (0xFF & crc >> 8) >> 4;
/* 119:176 */         crc <<= 4;
/* 120:177 */         int temp = 0xFF & (da ^ pByte[i] & 0xF);
/* 121:178 */         crc ^= crc_tb[temp];
/* 122:179 */         i++;
/* 123:    */       }
/* 124:181 */       int bCRCLow = 0xFF & crc;
/* 125:182 */       int bCRCHign = 0xFF & crc >> 8;
/* 126:183 */       if ((bCRCLow == 40) || (bCRCLow == 13) || (bCRCLow == 10)) {
/* 127:184 */         bCRCLow++;
/* 128:    */       }
/* 129:186 */       if ((bCRCHign == 40) || (bCRCHign == 13) || (bCRCHign == 10)) {
/* 130:187 */         bCRCHign++;
/* 131:    */       }
/* 132:189 */       crc = (0xFF & bCRCHign) << 8;
/* 133:190 */       return crc + bCRCLow;
/* 134:    */     }
/* 135:    */     catch (Exception ex)
/* 136:    */     {
/* 137:193 */       ex.printStackTrace();
/* 138:    */     }
/* 139:195 */     return 0;
/* 140:    */   }
/* 141:    */   
/* 142:    */   public static void main(String[] args)
/* 143:    */   {
/* 144:199 */     String re = "QCHGS";
/* 145:200 */     System.out.println("CRCByte是:" + new String(getCRCByte(re)));
/* 146:    */     
/* 147:202 */     System.out.println("CRCStr=:" + getCRC(re));
/* 148:    */   }
/* 149:    */   
/* 150:    */   public static String toStringHex(String s)
/* 151:    */   {
/* 152:207 */     byte[] baKeyword = new byte[s.length() / 2];
/* 153:208 */     for (int i = 0; i < baKeyword.length; i++) {
/* 154:    */       try
/* 155:    */       {
/* 156:210 */         baKeyword[i] = 
/* 157:211 */           ((byte)(0xFF & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16)));
/* 158:    */       }
/* 159:    */       catch (Exception e)
/* 160:    */       {
/* 161:213 */         e.printStackTrace();
/* 162:    */       }
/* 163:    */     }
/* 164:    */     try
/* 165:    */     {
/* 166:218 */       s = new String(baKeyword, "utf-8");
/* 167:    */     }
/* 168:    */     catch (Exception e1)
/* 169:    */     {
/* 170:220 */       e1.printStackTrace();
/* 171:    */     }
/* 172:222 */     return s;
/* 173:    */   }
/* 174:    */   
/* 175:228 */   private static String hexString = "0123456789ABCDEF";
/* 176:    */   
/* 177:    */   public static String encode(String str)
/* 178:    */   {
/* 179:235 */     byte[] bytes = str.getBytes();
/* 180:236 */     StringBuilder sb = new StringBuilder(bytes.length * 2);
/* 181:238 */     for (int i = 0; i < bytes.length; i++)
/* 182:    */     {
/* 183:239 */       sb.append(hexString.charAt((bytes[i] & 0xF0) >> 4));
/* 184:240 */       sb.append(hexString.charAt((bytes[i] & 0xF) >> 0));
/* 185:    */     }
/* 186:242 */     return sb.toString();
/* 187:    */   }
/* 188:    */   
/* 189:    */   public static String decode(String bytes)
/* 190:    */   {
/* 191:249 */     ByteArrayOutputStream baos = new ByteArrayOutputStream(
/* 192:250 */       bytes.length() / 2);
/* 193:252 */     for (int i = 0; i < bytes.length(); i += 2) {
/* 194:253 */       baos.write(hexString.indexOf(bytes.charAt(i)) << 4 | 
/* 195:254 */         hexString.indexOf(bytes.charAt(i + 1)));
/* 196:    */     }
/* 197:255 */     return new String(baos.toByteArray());
/* 198:    */   }
/* 199:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.util.CRCUtil
 * JD-Core Version:    0.7.0.1
 */