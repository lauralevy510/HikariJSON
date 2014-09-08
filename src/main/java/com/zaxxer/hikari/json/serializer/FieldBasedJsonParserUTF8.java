package com.zaxxer.hikari.json.serializer;

import java.lang.reflect.Field;

public class FieldBasedJsonParserUTF8 extends BaseJsonParserUTF8
{

   @Override
   protected void setMember(Object target, Object value, Object member)
   {
      try {
         Field declaredField = target.getClass().getDeclaredField((String) member);
         declaredField.setAccessible(true);
         if (value == Void.TYPE) {
            declaredField.set(target, null);
         }
         else {
            declaredField.set(target, value);
         }
      }
      catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
         throw new RuntimeException(e);
      }
   }

   @Override
   protected Class<?> getMemberType(String memberName, Class<?> valueType)
   {
      try {
         Field declaredField = valueType.getDeclaredField(memberName);
         return declaredField.getType();
      }
      catch (NoSuchFieldException | SecurityException e) {
         throw new RuntimeException(e);
      }
   }
}