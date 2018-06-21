/*    */ package com.fairyland.jdp.orm.util.jackson.serializer;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DateSerializer extends JsonSerializer<Date>
/*    */ {
/*    */   public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider)
/*    */     throws IOException, JsonProcessingException
/*    */   {
/* 15 */     jgen.writeString(value.toString());
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\fantasy.jar
 * Qualified Name:     ace.cn.core.util.jackson.serializer.DateSerializer
 * JD-Core Version:    0.6.0
 */