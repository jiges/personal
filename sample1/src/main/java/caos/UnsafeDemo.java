package caos;

import compare.Person;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.Objects;

public class UnsafeDemo {

    public static Unsafe UNSAFE = null;

    static boolean is64bit = true;

    static {
        Field field = null;
        try {
            field = Unsafe.class.getDeclaredField("theUnsafe");
            // 设置该Field为可访问
            field.setAccessible(true);
            // 通过Field得到该Field对应的具体对象，传入null是因为该Field为static的
            UNSAFE = (Unsafe) field.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) throws NoSuchFieldException {
        Employee employee = new Employee();

        Class employeeClass = employee.getClass();
        Field name = employeeClass.getDeclaredField("name");
//        Field age = employeeClass.getDeclaredField("age");
        System.out.println(UNSAFE.objectFieldOffset(name));
//        System.out.println(UNSAFE.objectFieldOffset(age));

        printAddresses("employee",employee);
        employee.say();
    }

    public static void printAddresses(String label, Object... objects) {
        System.out.print(label + ": 0x");
        long last = 0;
        int offset = UNSAFE.arrayBaseOffset(objects.getClass());
        int scale = UNSAFE.arrayIndexScale(objects.getClass());
        switch (scale) {
            case 4:
                long factor = is64bit ? 8 : 1;
                final long i1 = (UNSAFE.getInt(objects, offset) & 0xFFFFFFFFL) * factor;
                System.out.print(Long.toHexString(i1));
                last = i1;
                for (int i = 1; i < objects.length; i++) {
                    final long i2 = (UNSAFE.getInt(objects, offset + i * 4) & 0xFFFFFFFFL) * factor;
                    if (i2 > last)
                        System.out.print(", +" + Long.toHexString(i2 - last));
                    else
                        System.out.print(", -" + Long.toHexString( last - i2));
                    last = i2;
                }
                break;
            case 8:
                throw new AssertionError("Not supported");
        }
        System.out.println();
    }
}
