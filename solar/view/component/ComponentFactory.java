/*  1:   */ package cn.com.voltronic.solar.view.component;
/*  2:   */ 
/*  3:   */ import java.util.Date;
/*  4:   */ import javax.swing.JFormattedTextField;
/*  5:   */ import javax.swing.JSpinner;
/*  6:   */ import javax.swing.JSpinner.DateEditor;
/*  7:   */ import javax.swing.JSpinner.NumberEditor;
/*  8:   */ import javax.swing.SpinnerDateModel;
/*  9:   */ import javax.swing.SpinnerNumberModel;
/* 10:   */ 
/* 11:   */ public class ComponentFactory
/* 12:   */ {
/* 13:   */   public static AAComboBox createComboBox(String[] selects, String value)
/* 14:   */   {
/* 15:21 */     AAComboBox comboBox = new AAComboBox();
/* 16:22 */     comboBox.setModel(selects);
/* 17:   */     try
/* 18:   */     {
/* 19:24 */       comboBox.setSelectedItem(value);
/* 20:   */     }
/* 21:   */     catch (Exception localException) {}
/* 22:28 */     return comboBox;
/* 23:   */   }
/* 24:   */   
/* 25:   */   public static JSpinner createNumberSpinner(double min, double max, double step, double value)
/* 26:   */   {
/* 27:32 */     SpinnerNumberModel model = new SpinnerNumberModel();
/* 28:33 */     model.setMinimum(Double.valueOf(min));
/* 29:34 */     model.setMaximum(Double.valueOf(max));
/* 30:35 */     model.setStepSize(Double.valueOf(step));
/* 31:36 */     model.setValue(Double.valueOf(value));
/* 32:37 */     JSpinner jSpinner = new JSpinner(model);
/* 33:38 */     JSpinner.NumberEditor numberEditor = new JSpinner.NumberEditor(jSpinner);
/* 34:39 */     numberEditor.getTextField().setEditable(true);
/* 35:40 */     numberEditor.getTextField().setBackground(I18NListener.bgColor);
/* 36:41 */     jSpinner.setEditor(numberEditor);
/* 37:42 */     return jSpinner;
/* 38:   */   }
/* 39:   */   
/* 40:   */   public static JSpinner createNumberSpinner(int min, int max, int step, int value)
/* 41:   */   {
/* 42:46 */     SpinnerNumberModel model = new SpinnerNumberModel();
/* 43:47 */     model.setMinimum(Integer.valueOf(min));
/* 44:48 */     model.setMaximum(Integer.valueOf(max));
/* 45:49 */     model.setStepSize(Integer.valueOf(step));
/* 46:50 */     model.setValue(Integer.valueOf(value));
/* 47:51 */     JSpinner jSpinner = new JSpinner(model);
/* 48:52 */     JSpinner.NumberEditor numberEditor = new JSpinner.NumberEditor(jSpinner);
/* 49:53 */     numberEditor.getTextField().setEditable(true);
/* 50:54 */     numberEditor.getTextField().setBackground(I18NListener.bgColor);
/* 51:55 */     jSpinner.setEditor(numberEditor);
/* 52:56 */     return jSpinner;
/* 53:   */   }
/* 54:   */   
/* 55:   */   public static JSpinner createDateSpinner(Date date)
/* 56:   */   {
/* 57:60 */     SpinnerDateModel model = new SpinnerDateModel(date, null, null, 2);
/* 58:61 */     JSpinner jSpinner = new JSpinner(model);
/* 59:62 */     JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(jSpinner, "HH:mm");
/* 60:63 */     dateEditor.getTextField().setEditable(false);
/* 61:64 */     dateEditor.getTextField().setBackground(I18NListener.bgColor);
/* 62:65 */     jSpinner.setEditor(dateEditor);
/* 63:66 */     return jSpinner;
/* 64:   */   }
/* 65:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.component.ComponentFactory
 * JD-Core Version:    0.7.0.1
 */