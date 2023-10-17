
import java.util.*;
import java.lang.reflect.*;


public class Inspector
{
    public Inspector() { }

    public void inspect(Object obj, boolean recursive)
    {
        if (recursive)
        {
            recursiveInspect(obj);
        }
        notRecursiveInspect(obj);
    }

    public void recursiveInspect(Object obj){

    }
   
    public void notRecursiveInspect(Object obj) {
        Class<?> classObject = obj.getClass();
        Class<?> superClass = classObject.getSuperclass();
        Class<?>[] interfaceClass = classObject.getInterfaces();
        Constructor<?>[] constructorObject = classObject.getDeclaredConstructors();
        Method[] methodObjects = classObject.getDeclaredMethods();
        Field[] fieldObject = classObject.getDeclaredFields();

        
        System.out.println("Class: " + classObject.getName() + "\n");
        System.out.println("Superclass of current object is: " + superClass.getName() + "\n");
        if(interfaceClass.length > 0)
        {
            for(Class<?> interfaces : interfaceClass)
            {
                System.out.println("Interfaces implemented by current object are: " + interfaces.getName() + "\n");
            }
        }

        if(methodObjects.length > 0)
        {
            for(Method methods : methodObjects)
            {
                System.out.println("---------------------------------------------------------------------------");
                System.out.println("Methods declared in the current object are: " + methods.getName() + "\n");
                Class<?>[] exceptionObjects = methods.getExceptionTypes();
                Class<?>[] parameterObjects = methods.getParameterTypes();
                for(Class<?> exceptions : exceptionObjects)
                {
                     System.out.println("Exceptions declared in the current method are: " + exceptions.getName() + "\n");
                }
                 for(Class<?> parameters : parameterObjects)
                {
                     System.out.println("The paramater types declared in the current method are: " + parameters.getName() + "\n");
                }
                System.out.println("The return type for the declared method in the current object is: " + methods.getReturnType() + "\n");
                System.out.println("Modifiers for the declared method in the current object are: " + methods.getModifiers() + "\n");
            }
            System.out.println("---------------------------------------------------------------------------");
        }

        if(constructorObject.length > 0)
        {
            for(Constructor<?> constructors : constructorObject)
            {
                 System.out.println("---------------------------------------------------------------------------");
                 System.out.println("Contructors declared are: "  + constructors.getName() + "\n");
                 Class<?>[] parameterObjects = constructors.getParameterTypes();
                 for(Class<?> parameters : parameterObjects)
                {
                     System.out.println("The paramater types declared in the current constructor are: " + parameters.getName() + "\n");
                }
                System.out.println("Modifiers for the constructor in the current object are: " + constructors.getModifiers() + "\n");
            }
             System.out.println("---------------------------------------------------------------------------");
        }

        if(fieldObject.length > 0)
        {
            for(Field fields : fieldObject)
            {
                System.out.println("---------------------------------------------------------------------------");
                Class<?> fieldType = fields.getType();
                System.out.println("The fields declared in the class " + classObject.getName() + " are: "  + fields.getName() + "\n");
                System.out.println("The modifiers declared for this field are: " + fields.getModifiers() + "\n");
                if(fieldType.isArray())
                {
                    Class<?> componentType = fieldType.getComponentType();
                    System.out.println("The type declared for this field is: " + componentType.getName() + "[]" + "\n");
                }
                else
                {
                    System.out.println("The type declared for this field are: " + fieldType.getName() + "\n");
                }
            }
        }
    }
}