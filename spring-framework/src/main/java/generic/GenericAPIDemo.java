/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Java 泛型 API 示例
 */
public class GenericAPIDemo {

  public static void main(String[] args) {

    // 原生类型 primitive types : int long float
    Class intClass = int.class;
    System.out.println("intClass = " + intClass);

    // 数组类型 array types : int[],Object[]
    Class objectArrayClass = Object[].class;
    System.out.println("objectArrayClass = " + objectArrayClass);

    // 原始类型 raw types : java.lang.Integer
    Class IntegerRawClass = Integer.class;
    System.out.println("IntegerRawClass = " + IntegerRawClass);

    System.out.println("ArrayList.class = " + ArrayList.class);
    // 泛型参数类型 parameterized type
    ParameterizedType arrayListParameterizedType = (ParameterizedType) ArrayList.class
        .getGenericSuperclass();
    System.out.println("ArrayListParameterizedType = " + arrayListParameterizedType);
    System.out.println(
        "arrayListParameterizedType.getRawType() = " + arrayListParameterizedType.getRawType());

    System.out.println("泛型类型变量 Type Variable");
    // <E>
    Type[] typeVariables = arrayListParameterizedType.getActualTypeArguments();
    Stream.of(typeVariables)
        .map(TypeVariable.class::cast) // Type -> TypeVariable
        .forEach(System.out::println);

  }
}
