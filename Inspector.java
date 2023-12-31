import java.lang.reflect.*;

public class Inspector
{
    public Inspector() { }

    public void inspect(Object obj, boolean recursive) throws IllegalArgumentException, IllegalAccessException 
    {
        //Instantiate my objects to use reflection
        Class<?> classObject = obj.getClass();
        Class<?> superClass = classObject.getSuperclass();
        Class<?>[] interfaceClass = classObject.getInterfaces();
        Constructor<?>[] constructorObject = classObject.getDeclaredConstructors();
        Method[] methodObjects = classObject.getDeclaredMethods();
        Field[] fieldObject = classObject.getDeclaredFields();
        
        //Print the class name and immediate superclass name
        System.out.println("Class: " + classObject.getName() + "\n");  
        System.out.println("Superclass: " + superClass.getName() + "\n");
       //Check if interaces are being imeplemented
        interface_info(interfaceClass);
        //get method information
        method_info(methodObjects);
        //get constrcutor information
        constructor_info(constructorObject);
        //get field information
        field_info(fieldObject, obj, recursive);
    }

    //Get interface name
    public void interface_info(Class<?>[] interfaceClass)
    {
        if(interfaceClass.length > 0)
        {
            for(Class<?> interfaces : interfaceClass)
            {
                System.out.println("Interface Name: " + interfaces.getName() + "\n");
            }
        } 
    }

    //Gets methods parameter types, exceptions, name and return type
    public void method_info(Method[] methodObjects)
    {
    if(methodObjects.length > 0)
        {
            System.out.println("----------------------START OF METHODS-----------------------------------------------------");
            for(Method methods : methodObjects)
            {
                System.out.println("---------------------------------------------------------------------------");
                System.out.println("Method Name: " + methods.getName() + "\n");
                Class<?>[] exceptionObjects = methods.getExceptionTypes();
                Class<?>[] parameterObjects = methods.getParameterTypes();
                for(Class<?> exceptions : exceptionObjects)
                {
                     System.out.println("Method Exception: " + exceptions.getName() + "\n");
                }
                 for(Class<?> parameters : parameterObjects)
                {
                    if(parameters.isArray())
                    {
                        Class<?> componentType = parameters.getComponentType();
                        System.out.println("Method Parameter Type: " + componentType.getName() + "[]" + "\n");
                    }
                    else
                    {
                        System.out.println("Method Parameter Type: " + parameters.getName() + "\n");
                    } 
                }
                 if(methods.getReturnType().isArray())
                    {
                        Class<?> componentType = methods.getReturnType().getComponentType();
                        System.out.println("Method Return Type: " + componentType.getName() + "[]" + "\n");
                    }
                    else
                    {
                        System.out.println("Method Return Type: " + methods.getReturnType() + "\n");
                    } 
                System.out.println("Method Modifier: " + methods.getModifiers() + "\n");
            }
            System.out.println("-----------------------END OF METHODS----------------------------------------------------");
        }
    }

    //Gets constructor modifiers, name and parameter types
    public void constructor_info(Constructor<?>[] constructorObject)
    {
        if(constructorObject.length > 0)
        {
            System.out.println("---------------------START OF CONSTRUCTORS------------------------------------------------------");
            for(Constructor<?> constructors : constructorObject)
            {
                 System.out.println("---------------------------------------------------------------------------");
                 System.out.println("Constructor Name: "  + constructors.getName() + "\n");
                 Class<?>[] parameterObjects = constructors.getParameterTypes();
                 for(Class<?> parameters : parameterObjects)
                {
                    if(parameters.isArray())
                    {
                        Class<?> componentType = parameters.getComponentType();
                        System.out.println("Constructor Parameter Type: " + componentType.getName() + "[]" + "\n");
                    }
                    else
                    {
                        System.out.println("Constructor Parameter Type: " + parameters.getName() + "\n");
                    }
                }
                System.out.println("Constructor Modifiers: " + constructors.getModifiers() + "\n");
            }
             System.out.println("-----------------------END OF CONSTRUCTORS----------------------------------------------------");
        }

    }

    //Gets fields modifiers, name, type and value
    public void field_info(Field[] fieldObject, Object obj,  Boolean recursive) throws IllegalArgumentException, IllegalAccessException
    {
        if(fieldObject.length > 0)
        {
            System.out.println("--------------------------START OF FIELDS-------------------------------------------------");
            for(Field fields : fieldObject)
            {
                Class<?> fieldType = fields.getType();
                System.out.println("---------------------------------------------------------------------------");
                System.out.println("Field Name: " + fields.getName() + "\n");
                System.out.println("Field Modifiers: " + fields.getModifiers() + "\n");
                if(fieldType.isArray())
                {
                    Class<?> componentType = fieldType.getComponentType();
                    System.out.println("Field Type: " + componentType.getName() + "[]" + "\n");
                }
                else
                {
                    System.out.println("Field Type: " + fieldType.getName() + "\n");
                }    
                /* NOT RIGHT NEED TO FIX ----------------------------------------------------------------------------------------------------------------------------- */
                if(!fieldType.isPrimitive() && !recursive)
                {
                    System.out.println("Field Value: " + fields.getName() + fields.hashCode());
                }
                else
                {
                    fields.setAccessible(true);
                    Object value = fields.get(obj);
                    System.out.println("Field Value: " + value);
                }

                if(!fieldType.isPrimitive() && recursive)
                {
                     if(fields.get(obj) != null)
                     {
                        System.out.println("--------------------------END OF FIELDS-------------------------------------------------");
                        inspect(fields.get(obj), recursive);
                     }
                } 
            }
        }
    }
}

   