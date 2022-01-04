package utill;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * @version :v1.00
 */
public class DataType {
    public static final String DATATYPE_STRING = "String";

    public static final String DATATYPE_SHORT = "Short";

    public static final String DATATYPE_INTEGER = "Integer";

    public static final String DATATYPE_LONG = "Long";

    public static final String DATATYPE_DOUBLE = "Double";

    public static final String DATATYPE_FLOAT = "Float";

    public static final String DATATYPE_BYTE = "Byte";

    public static final String DATATYPE_CHAR = "Char";

    public static final String DATATYPE_BOOLEAN = "Boolean";

    public static final String DATATYPE_DATE = "Date";

    public static final String DATATYPE_TIME = "Time";

    public static final String DATATYPE_DATETIME = "DateTime";

    public static final String DATATYPE_OBJECT = "Object";

    public static final String DATATYPE_short = "short";

    public static final String DATATYPE_int = "int";

    public static final String DATATYPE_long = "long";

    public static final String DATATYPE_double = "double";

    public static final String DATATYPE_float = "float";

    public static final String DATATYPE_byte = "byte";

    public static final String DATATYPE_char = "char";

    public static final String DATATYPE_boolean = "boolean";

    public static boolean isNeedFullClassName(String type) {
        if (type.equals("String")) return false;
        if (type.equals("Short")) return false;
        if (type.equals("Integer")) return false;
        if (type.equals("Long")) return false;
        if (type.equals("Double")) return false;
        if (type.equals("Float")) return false;
        if (type.equals("Byte")) return false;
        if (type.equals("Char")) return false;
        if (type.equals("Boolean")) return false;
        if (type.equals("Date")) return true;
        if (type.equals("Time")) return true;
        if (type.equals("DateTime")) return true;

        if (type.equals("Object")) return false;

        if (type.equals("short")) return false;
        if (type.equals("int")) return false;
        if (type.equals("long")) return false;
        if (type.equals("double")) return false;
        if (type.equals("float")) return false;
        if (type.equals("byte")) return false;
        if (type.equals("char")) return false;
        if (type.equals("boolean")) return false;

        return true;
    }

    public static String getJavaObjectType(String type) {
        if (type.equalsIgnoreCase("String")) return "String";
        if ((type.equalsIgnoreCase("Short")) || (type.equalsIgnoreCase("short"))) return "Short";
        if ((type.equalsIgnoreCase("Integer")) || (type.equalsIgnoreCase("int"))) return "Integer";
        if ((type.equalsIgnoreCase("Long")) || (type.equalsIgnoreCase("long"))) return "Long";
        if ((type.equalsIgnoreCase("Double")) || (type.equalsIgnoreCase("double"))) return "Double";
        if ((type.equalsIgnoreCase("Float")) || (type.equalsIgnoreCase("float"))) return "Float";
        if ((type.equalsIgnoreCase("Byte")) || (type.equalsIgnoreCase("byte"))) return "Byte";
        if ((type.equalsIgnoreCase("Char")) || (type.equalsIgnoreCase("char"))) return "Character";
        if ((type.equalsIgnoreCase("Boolean")) || (type.equalsIgnoreCase("boolean"))) return "Boolean";
        if (type.equalsIgnoreCase("Date")) return "Date";
        if (type.equalsIgnoreCase("Time")) return "Time";
        if (type.equalsIgnoreCase("DateTime")) return "Timestamp";
        return type;
    }

    public static boolean isSimpleDataType(String type) {
        if (type.equalsIgnoreCase("String")) return false;
        if (type.equalsIgnoreCase("Short")) return true;
        if (type.equalsIgnoreCase("short")) return true;
        if (type.equalsIgnoreCase("Integer")) return true;
        if (type.equalsIgnoreCase("int")) return true;
        if (type.equalsIgnoreCase("Long")) return true;
        if (type.equalsIgnoreCase("long")) return true;
        if (type.equalsIgnoreCase("Double")) return true;
        if (type.equalsIgnoreCase("double")) return true;
        if (type.equalsIgnoreCase("Float")) return true;
        if (type.equalsIgnoreCase("float")) return true;
        if (type.equalsIgnoreCase("Byte")) return true;
        if (type.equalsIgnoreCase("byte")) return true;
        if (type.equalsIgnoreCase("Char")) return true;
        if (type.equalsIgnoreCase("char")) return true;
        if (type.equalsIgnoreCase("Boolean")) return true;
        if (type.equalsIgnoreCase("boolean")) return true;
        if (type.equalsIgnoreCase("Date")) return false;
        if (type.equalsIgnoreCase("Time")) return false;
        if (type.equalsIgnoreCase("DateTime")) return false;
        return false;
    }

    public static Class getSimpleDataType(Class aClass) {
        if (Integer.class.equals(aClass)) return Integer.TYPE;
        if (Short.class.equals(aClass)) return Short.TYPE;
        if (Long.class.equals(aClass)) return Long.TYPE;
        if (Double.class.equals(aClass)) return Double.TYPE;
        if (Float.class.equals(aClass)) return Float.TYPE;
        if (Byte.class.equals(aClass)) return Byte.TYPE;
        if (Character.class.equals(aClass)) return Character.TYPE;
        if (Boolean.class.equals(aClass)) return Boolean.TYPE;
        return aClass;
    }

    public static String getNullValueString(String type) {
        if (type.equalsIgnoreCase("String")) return "null";
        if (type.equalsIgnoreCase("Short")) return "(short)0";
        if (type.equalsIgnoreCase("Integer")) return "0";
        if (type.equalsIgnoreCase("Long")) return "0";
        if (type.equalsIgnoreCase("Double")) return "0";
        if (type.equalsIgnoreCase("Float")) return "0";
        if (type.equalsIgnoreCase("Byte")) return "((byte)0)";
        if (type.equalsIgnoreCase("Char")) return "((char)0)";
        if (type.equalsIgnoreCase("Boolean")) return "false";
        if (type.equalsIgnoreCase("Date")) return "null";
        if (type.equalsIgnoreCase("Time")) return "null";
        if (type.equalsIgnoreCase("DateTime")) return "null";
        return "null";
    }

    public static String getToSimpleDataTypeFunction(String type) {
        if (type.equalsIgnoreCase("String")) return "";
        if ((type.equalsIgnoreCase("Short")) || (type.equalsIgnoreCase("short"))) return "shortValue";
        if ((type.equalsIgnoreCase("Integer")) || (type.equalsIgnoreCase("int"))) return "intValue";
        if ((type.equalsIgnoreCase("Long")) || (type.equalsIgnoreCase("long"))) return "longValue";
        if ((type.equalsIgnoreCase("Double")) || (type.equalsIgnoreCase("double"))) return "doubleValue";
        if ((type.equalsIgnoreCase("Float")) || (type.equalsIgnoreCase("float"))) return "floatValue";
        if ((type.equalsIgnoreCase("Byte")) || (type.equalsIgnoreCase("byte"))) return "byteValue";
        if ((type.equalsIgnoreCase("Char")) || (type.equalsIgnoreCase("char"))) return "charValue";
        if ((type.equalsIgnoreCase("Boolean")) || (type.equalsIgnoreCase("boolean"))) return "booleanValue";
        if (type.equalsIgnoreCase("Date")) return "";
        if (type.equalsIgnoreCase("Time")) return "";
        if (type.equalsIgnoreCase("DateTime")) return "";
        return "";
    }

    public static String getToSimpleDataTypeFunction(Class type) {
        if ((type.equals(Short.class)) || (type.equals(Short.TYPE))) return "shortValue";
        if ((type.equals(Integer.class)) || (type.equals(Integer.TYPE))) return "intValue";
        if ((type.equals(Long.class)) || (type.equals(Long.TYPE))) return "longValue";
        if ((type.equals(Double.class)) || (type.equals(Double.TYPE))) return "doubleValue";
        if ((type.equals(Float.class)) || (type.equals(Float.TYPE))) return "floatValue";
        if ((type.equals(Byte.class)) || (type.equals(Byte.TYPE))) return "byteValue";
        if ((type.equals(Character.class)) || (type.equals(Character.TYPE))) return "charValue";
        if ((type.equals(Boolean.class)) || (type.equals(Boolean.TYPE))) return "booleanValue";
        return "";
    }

    public static String getString(Object obj) {
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    public static short getShort(Object obj) {
        if (obj == null)
            return 0;
        if ((obj instanceof Number)) {
            return ((Number) obj).shortValue();
        }
        return ((Short) transfer(obj, Short.class)).shortValue();
    }

    public static int getInt(Object obj) {
        if (obj == null)
            return 0;
        if ((obj instanceof Number)) {
            return ((Number) obj).intValue();
        }
        return ((Integer) transfer(obj, Integer.class)).intValue();
    }

    public static long getLong(Object obj) {
        if (obj == null)
            return 0L;
        if ((obj instanceof Number)) {
            return ((Number) obj).longValue();
        }
        return ((Long) transfer(obj, Long.class)).longValue();
    }

    public static double getDouble(Object obj) {
        if (obj == null)
            return 0.0D;
        if ((obj instanceof Number)) {
            return ((Number) obj).doubleValue();
        }
        return ((Double) transfer(obj, Double.class)).doubleValue();
    }

    public static float getFloat(Object obj) {
        if (obj == null)
            return 0.0F;
        if ((obj instanceof Number)) {
            return ((Number) obj).floatValue();
        }
        return ((Float) transfer(obj, Float.class)).floatValue();
    }

    public static byte getByte(Object obj) {
        if (obj == null)
            return 0;
        if ((obj instanceof Number)) {
            return ((Number) obj).byteValue();
        }
        return ((Byte) transfer(obj, Byte.class)).byteValue();
    }

    public static boolean getBoolean(Object obj) {
        if (obj == null)
            return false;
        if ((obj instanceof Boolean)) {
            return ((Boolean) obj).booleanValue();
        }
        return ((Boolean) transfer(obj, Boolean.class)).booleanValue();
    }

    public static char getChar(Object obj) {
        if (obj == null)
            return '\000';
        if ((obj instanceof Character))
            return ((Character) obj).charValue();
        if (((obj instanceof String)) && (((String) obj).length() == 1)) {
            return ((String) obj).charAt(0);
        }
        return ((Character) transfer(obj, Character.class)).charValue();
    }

    public static String getClassName(Class className) {
        String name = className.getName();
        return getClassName(name);
    }

    public static String getClassName(String name) {
        String arrays = "";
        if (name.indexOf("[") >= 0) {
            int point = 0;
            while (name.charAt(point) == '[') {
                arrays = arrays + "[]";
                point++;
            }
            if (name.charAt(point) == 'L')
                name = name.substring(point + 1, name.length() - 1);
            else if (name.charAt(point) == 'Z')
                name = "boolean";
            else if (name.charAt(point) == 'B')
                name = "byte";
            else if (name.charAt(point) == 'C')
                name = "char";
            else if (name.charAt(point) == 'D')
                name = "double";
            else if (name.charAt(point) == 'F')
                name = "float";
            else if (name.charAt(point) == 'I')
                name = "int";
            else if (name.charAt(point) == 'J')
                name = "long";
            else if (name.charAt(point) == 'S') {
                name = "short";
            }
        }
        int index = name.lastIndexOf('.');
        if ((index > 0) && (name.substring(0, index).equals("java.lang") == true)) {
            name = name.substring(index + 1);
        }
        name = name + arrays;
        return name;
    }

    public static String[] getDataTypeNames() {
        return new String[]{"String", "Short", "Integer", "Long", "Double", "Float", "Byte", "Char", "Boolean", "Date", "Time", "DateTime", "Object", "short", "int", "long", "long", "float", "byte", "char", "boolean", "UserInfoInterface"};
    }

    public static Class getPrimitiveClass(Class type) {
        if (type.equals(Short.TYPE)) return Short.class;
        if (type.equals(Integer.TYPE)) return Integer.class;
        if (type.equals(Long.TYPE)) return Long.class;
        if (type.equals(Double.TYPE)) return Double.class;
        if (type.equals(Float.TYPE)) return Float.class;
        if (type.equals(Byte.TYPE)) return Byte.class;
        if (type.equals(Character.TYPE)) return Character.class;
        if (type.equals(Boolean.TYPE)) return Boolean.class;
        return type;
    }

    public static Class getSimpleClass(Class type) {
        if (type.equals(Short.class)) return Short.TYPE;
        if (type.equals(Integer.class)) return Integer.TYPE;
        if (type.equals(Long.class)) return Long.TYPE;
        if (type.equals(Double.class)) return Double.TYPE;
        if (type.equals(Float.class)) return Float.TYPE;
        if (type.equals(Byte.class)) return Byte.TYPE;
        if (type.equals(Character.class)) return Character.TYPE;
        if (type.equals(Boolean.class)) return Boolean.TYPE;
        return type;
    }

    public static String getPrimitiveClass(String type) {
        if (type.equals("short")) return Short.class.getName();
        if (type.equals("int")) return Integer.class.getName();
        if (type.equals("long")) return Long.class.getName();
        if (type.equals("double")) return Double.class.getName();
        if (type.equals("float")) return Float.class.getName();
        if (type.equals("byte")) return Byte.class.getName();
        if (type.equals("char")) return Character.class.getName();
        if (type.equals("boolean")) return Boolean.class.getName();
        return type;
    }

    static int findMostSpecificSignature(Class[] idealMatch, Class[][] candidates) {
        Class[] bestMatch = null;
        int bestMatchIndex = -1;

        for (int i = candidates.length - 1; i >= 0; i--) {
            Class[] targetMatch = candidates[i];
            if ((isSignatureAssignable(idealMatch, targetMatch)) && ((bestMatch == null) || (isSignatureAssignable(targetMatch, bestMatch)))) {
                bestMatch = targetMatch;
                bestMatchIndex = i;
            }
        }

        if (bestMatch != null) {
            return bestMatchIndex;
        }
        return -1;
    }

    static boolean isSignatureAssignable(Class[] from, Class[] to) {
        for (int i = 0; i < from.length; i++)
            if (!isAssignable(to[i], from[i]))
                return false;
        return true;
    }

    public static boolean isAssignable(Class dest, Class sour) {
        if (dest == sour) {
            return true;
        }
        if (dest == null)
            return false;
        if (sour == null) {
            return !dest.isPrimitive();
        }
        if ((dest.isPrimitive()) && (sour.isPrimitive())) {
            if (dest == sour) {
                return true;
            }
            if ((sour == Byte.TYPE) && ((dest == Short.TYPE) || (dest == Integer.TYPE) || (dest == Long.TYPE) || (dest == Float.TYPE) || (dest == Double.TYPE))) {
                return true;
            }
            if ((sour == Short.TYPE) && ((dest == Integer.TYPE) || (dest == Long.TYPE) || (dest == Float.TYPE) || (dest == Double.TYPE))) {
                return true;
            }
            if ((sour == Character.TYPE) && ((dest == Integer.TYPE) || (dest == Long.TYPE) || (dest == Float.TYPE) || (dest == Double.TYPE))) {
                return true;
            }
            if ((sour == Integer.TYPE) && ((dest == Long.TYPE) || (dest == Float.TYPE) || (dest == Double.TYPE))) {
                return true;
            }
            if ((sour == Long.TYPE) && ((dest == Float.TYPE) || (dest == Double.TYPE))) {
                return true;
            }
            if ((sour == Float.TYPE) && (dest == Double.TYPE)) {
                return true;
            }
        } else if (dest.isAssignableFrom(sour)) {
            return true;
        }
        return false;
    }

    public static Object transfer(Object value, Class type) {
        if (value == null) return null;
        if (((value instanceof String)) && (value.toString().trim().equals(""))) {
            if (String.class.equals(type)) {
                return value;
            }
            return null;
        }

        if ((type.equals(Short.class)) || (type.equals(Short.TYPE))) {
            if ((value instanceof Short)) {
                return value;
            }
            return new Short(new BigDecimal(value.toString()).shortValue());
        }
        if ((type.equals(Integer.class)) || (type.equals(Integer.TYPE))) {
            if ((value instanceof Integer)) {
                return value;
            }
            return new Integer(new BigDecimal(value.toString()).intValue());
        }
        if ((type.equals(Character.class)) || (type.equals(Character.TYPE))) {
            if ((value instanceof Character)) {
                return value;
            }
            return new Character(value.toString().charAt(0));
        }
        if ((type.equals(Long.class)) || (type.equals(Long.TYPE))) {
            if ((value instanceof Long)) {
                return value;
            }
            return new Long(new BigDecimal(value.toString()).longValue());
        }
        if (type.equals(String.class)) {
            if ((value instanceof String)) {
                return value;
            }
            return value.toString();
        }
        if (type.equals(java.sql.Date.class)) {
            if ((value instanceof java.sql.Date))
                return value;
            if ((value instanceof java.util.Date))
                return new java.sql.Date(((java.util.Date) value).getTime());
            try {
                SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd");
                return new java.sql.Date(a.parse(value.toString()).getTime());
            } catch (Exception e) {
                throw new RuntimeException("value: " + value + " 转 Date 失败");
            }
        }
        if (type.equals(Time.class)) {
            if ((value instanceof Time))
                return value;
            if ((value instanceof java.util.Date))
                return new Time(((java.util.Date) value).getTime());
            try {
                SimpleDateFormat a = new SimpleDateFormat("HH:mm:ss");
                return new Time(a.parse(value.toString()).getTime());
            } catch (Exception e) {
                throw new RuntimeException("value: " + value + " 转 Time 失败");
            }
        }
        if (type.equals(Timestamp.class)) {
            if ((value instanceof Timestamp))
                return value;
            if ((value instanceof java.util.Date))
                return new Timestamp(((java.util.Date) value).getTime());
            try {
                SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String tmpstr = value.toString();
                if (tmpstr.trim().length() <= 10)
                    tmpstr = tmpstr + " 00:00:00";
                return new Timestamp(a.parse(tmpstr).getTime());
            } catch (Exception e) {
                throw new RuntimeException("value: " + value + " 转 DateTime 失败");
            }
        }
        if ((type.equals(Double.class)) || (type.equals(Double.TYPE))) {
            if ((value instanceof Double)) {
                return value;
            }
            return new Double(new BigDecimal(value.toString()).doubleValue());
        }
        if ((type.equals(Float.class)) || (type.equals(Float.TYPE))) {
            if ((value instanceof Float)) {
                return value;
            }
            return new Float(new BigDecimal(value.toString()).floatValue());
        }
        if ((type.equals(Byte.class)) || (type.equals(Byte.TYPE))) {
            if ((value instanceof Byte)) {
                return value;
            }
            return new Byte(new BigDecimal(value.toString()).byteValue());
        }
        if ((type.equals(Boolean.class)) || (type.equals(Boolean.TYPE))) {
            if ((value instanceof Boolean))
                return value;
            if ((value instanceof Number)) {
                if (((Number) value).doubleValue() > 0.0D) {
                    return new Boolean(true);
                }
                return new Boolean(false);
            }
            if ((value instanceof String)) {
                if ((((String) value).equalsIgnoreCase("true")) || (((String) value).equalsIgnoreCase("y"))) {
                    return new Boolean(true);
                }
                return new Boolean(false);
            }
            throw new RuntimeException("value: " + value + " 转 Boolean 失败");
        }
        return value;
    }

    public static Object transfer(Object value, String type) {
        if (value == null) return null;
        if (((value instanceof String)) && (value.toString().trim().equals(""))) {
            if ("String".equalsIgnoreCase(type)) {
                return value;
            }
            return null;
        }
        if ((type.equalsIgnoreCase("Short")) || (type.equalsIgnoreCase("short"))) {
            if ((value instanceof Short)) {
                return value;
            }
            return new Short(new BigDecimal(value.toString()).shortValue());
        }
        if ((type.equalsIgnoreCase("Integer")) || (type.equalsIgnoreCase("int"))) {
            if ((value instanceof Integer)) {
                return value;
            }
            return new Integer(new BigDecimal(value.toString()).intValue());
        }
        if ((type.equalsIgnoreCase("Char")) || (type.equalsIgnoreCase("char"))) {
            if ((value instanceof Character)) {
                return value;
            }
            return new Character(value.toString().charAt(0));
        }
        if ((type.equalsIgnoreCase("Long")) || (type.equalsIgnoreCase("long"))) {
            if ((value instanceof Long)) {
                return value;
            }
            return new Long(new BigDecimal(value.toString()).longValue());
        }
        if (type.equalsIgnoreCase("String")) {
            if ((value instanceof String)) {
                return value;
            }
            return value.toString();
        }
        if (type.equalsIgnoreCase("Date")) {
            if ((value instanceof java.sql.Date))
                return value;
            if ((value instanceof Timestamp))
                return new java.sql.Date(((Timestamp) value).getTime());
            try {
                String tmpstr = value.toString().replace('/', '-');
                SimpleDateFormat DATA_FORMAT_yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
                return new java.sql.Date(DATA_FORMAT_yyyyMMdd.parse(tmpstr).getTime());
            } catch (Exception ex) {
                if ((ex instanceof RuntimeException)) {
                    throw ((RuntimeException) ex);
                }
                throw new RuntimeException("value: " + value + " 转 Date 失败", ex);
            }
        }

        if (type.equalsIgnoreCase("Time")) {
            if ((value instanceof Time))
                return value;
            if ((value instanceof Timestamp))
                return new Time(((Timestamp) value).getTime());
            try {
                SimpleDateFormat DATA_FORMAT_HHmmss = new SimpleDateFormat("HH:mm:ss");
                return new Time(DATA_FORMAT_HHmmss.parse(value.toString()).getTime());
            } catch (Exception e) {
                throw new RuntimeException("value: " + value + " 转 Time 失败", e);
            }
        }
        if (type.equalsIgnoreCase("DateTime")) {
            if ((value instanceof Timestamp))
                return value;
            if ((value instanceof java.util.Date))
                return new Timestamp(((java.util.Date) value).getTime());
            try {
                SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String tmpstr = value.toString();
                if (tmpstr.trim().length() <= 10)
                    tmpstr = tmpstr + " 00:00:00";
                return new Timestamp(a.parse(tmpstr).getTime());
            } catch (Exception e) {
                throw new RuntimeException("value: " + value + " 转 Timestamp 失败");
            }
        }
        if ((type.equalsIgnoreCase("Double")) || (type.equalsIgnoreCase("double"))) {
            if ((value instanceof Double)) {
                return value;
            }
            return new Double(new BigDecimal(value.toString()).doubleValue());
        }
        if ((type.equalsIgnoreCase("Float")) || (type.equalsIgnoreCase("float"))) {
            if ((value instanceof Float)) {
                return value;
            }
            return new Float(new BigDecimal(value.toString()).floatValue());
        }
        if ((type.equalsIgnoreCase("Byte")) || (type.equalsIgnoreCase("byte"))) {
            if ((value instanceof Byte)) {
                return value;
            }
            return new Byte(new BigDecimal(value.toString()).byteValue());
        }
        if ((type.equalsIgnoreCase("Boolean")) || (type.equalsIgnoreCase("boolean"))) {
            if ((value instanceof Boolean))
                return value;
            if ((value instanceof Number)) {
                if (((Number) value).doubleValue() > 0.0D) {
                    return new Boolean(true);
                }
                return new Boolean(false);
            }
            if ((value instanceof String)) {
                if ((((String) value).equalsIgnoreCase("true")) || (((String) value).equalsIgnoreCase("y"))) {
                    return new Boolean(true);
                }
                return new Boolean(false);
            }
            throw new RuntimeException("value: " + value + " 转 Boolean 失败");
        }

        return value;
    }
}
