## Top 10 insights from Java OCA certification

At Baltic Amadeus we strive to be the best professionals we possibly could. Expertise and Continuous learning being among our top priorities, we can not stop improving, validating our knowledge with a an array of highly valued certificates. As for Java developers, Oracle Certified Associate and Professional certifications are the number one destination. However, they are not an easy catch – examination is full of tricky questions, requiring the test taker to become a compiler for a few hours. In this blog post 10 Java tricks you might have forgotten or never even had a chance to glance at will be examined.

## 1. Default values

Let's start with something simple, though sometimes forgotten &mdash; default values of variables. In Java primitive variables have default values, like int taking value of 0, boolean becoming false, etc. 

For example:
```java
public class DefaultValues {
    private int x;
    @Test
    public void defaultValues() {
        int y = 10;
        assert(10, x+y);
    }
}
```
This test compiles and passes successfully, because *x* is int, which has default value of *0*.

But what if *x* is not a class variable, but a local one?
```java
public void defaultValues() {
    int y = 10;
    int x;
    assertEquals(10, y + x);
}
```
This code will not compile, as local variables need to be initialized before performing any operations on them, despite having default values. This is something to keep an eye on and could be a reason for multiple bugs, if not for the modern IDEs, which save us by pinpointing compile errors.

## 2. Naming
Next let's dwell into the curious cases of variable naming. 
Which of the following is valid?
```java
String A$B;           
String _helloWorld;   
String 1980_s;        
String true;          
```
Well, we, java developers, are used to the best practices of giving names to the various java components, so all the variables above might seem to be not accepted by the compiler. Interestingly, java accepts half of them. 

*A$B* is a valid name, as names might contain various symbols, including '$'. Also, names might start with '_'. But not with a number &mdash; so *1980_s* is not a valid name and compiler will fail here. Moreover, java have some reserved keywords, such as *class*, *if*, *new*, ..., and *true* as well, making the fourth string name invalid too. 

### 3. Numbers
In java numbers might have various forms. It's not only 0..9, but also letters and symbols. Let's check some examples.

```java
int    A = 1_234_000;
double B = 1_234_.0;
double C = 1_234._0;
double D = 1_234.0_;
double E = 1_234.0;
```
The '_' symbol might be used to separate logical parts of a number, like thousands. That's the only real case the underscore should be used. But it has some rules to follow &mdash; dot and underscore do not get along and it can not be applied to the beginning or the end of the number. By these rules, A and E will compile, others - not. 

The second example shows the use of the letters for numbers:
```java
int    A = 9L;
int    B = 0b101;
int    C = 0xE;
double D = 0xE;
```
A will fail, as '*L*' or '*l*' at the end of the number indicate a long number format, so it can not be assigned to int. 

B is ok - '*0b*' is a way to declare binary numbers since Java 7. B = 5.

C and D are good too - '*0x*' is the beginning of a hex number. So C = 14, D = 14.0.

### 4. Max number values
Be careful when dealing with max integer or long values. 
```java
System.out.println(Integer.MAX_VALUE);
```
The maximum value of an integer is *2147483647*. But what happens, if it is increased by one?
```java
System.out.println(Integer.MAX_VALUE+1);
```
Actually, integer goes negative. Result is *-2147483648*. What if it is increased further?
```java
System.out.println(Integer.MAX_VALUE+2);
```
Value remains negative, but acts predictable this time: *-2147483647*.

Same applies for long values:
```java
System.out.println(Long.MAX_VALUE);   //  9223372036854775807
System.out.println(Long.MAX_VALUE+1); // -9223372036854775808
```

### 5. Flow control
It is not often that you come across code like this:
```java
FIRST_LOOP: for (int a = 1; a <= 4; a++) {
    for (char x = 'a'; x <= 'c'; x++) {
        if (a == 2 || x == 'b')
            continue FIRST_LOOP;
        System.out.print(" " + a + x);
    }
}
```
Indeed, labels might be assigned to different parts of java code, making it possible to, for example, quit an outer loop. It is rarely utilized though - goto is anti pattern these days.

Result of this code is *' 1a 3a 4a'*.

### 6. String equality
Old topic, but easily forgotten, so let's repeat.

Equal or not?
```java
String x = new String("Hello World");
String y = "Hello World";
System.out.println(x == y);
```
Well, not, since == compares objects, and *new String* creates one, while *"some string"* fetches one from string pool (or places it before fetching). 

However, this example is true:
```java
String x = "Hello World";
String y = "Hello World";
System.out.println(x == y);
```
Both strings are taken from the pool here, thus they point to the same place in memory and are truly equal.

### 7. Binary search
One more underutilized java feature is binary search. It is a speedy way to search in an array. 
```java
int[] numbers = {2,4,6,8};
System.out.println(Arrays.binarySearch(numbers, 2)); // 0
System.out.println(Arrays.binarySearch(numbers, 4)); // 1
System.out.println(Arrays.binarySearch(numbers, 1)); // -1
System.out.println(Arrays.binarySearch(numbers, 3)); // -2
System.out.println(Arrays.binarySearch(numbers, 9)); // -5
```
Having an array of integers, *Arrays.binarySearch* enables us to find a position of an int in an array. Thus number 2 is found at position 0 and number 4 &mdash; at 1. 

When searching for values not in an array, result is calculated by a formula &mdash; a position, where number should be inserted minus 1. So as number 1 would be inserted at position 0, searching for it results in -1. Applying same rule to 3 and 9, gives us -2 and -5.

However, there is a small limitation &mdash; an array has to be sorted. No compile error or exception is produced in cases of unsorted array, but results of a binary search become undetermined:

```java
int[] numbers2 = {3,2,1};
System.out.println(Arrays.binarySearch(numbers2, 2)); // 1
System.out.println(Arrays.binarySearch(numbers2, 3)); // -4
```
When 2 is found at expected position 1, number 3 is somehow moved to -4, when expected result is 0. Don't be fooled, use with caution.

### 8. Array and list
While arrays and lists might seem to be similar, they have their differences. Let's check this example now:
```java
String[] array = { "baltic", "amadeus" };
List<String> list = Arrays.asList(array);
list.set(1, "IT");
array[0] = "enjoy";
for (String b : array) System.out.print(b + " ");
```
First an array is declared, containing two strings: "*baltic*" and "*amadeus*". Then, list is created from this array, using a *Arrays* utility method *asList*. List's second value is set to "*IT*". And finally our attention turns back to the array as it's first value is changed to "*enjoy*". Then content of array is printed. What is the result of this code?

Seems like a simple case. But don't rush here, there is a small but. When creating a list from an array, we actually make it to point to the same place in memory as the original array. They literally share their values. When one is changed, other is changed too. Having that in mind, we get an "*enjoy IT*" as an output.

In this case list also inherits some other array properties &mdash; a fixed size to be specific. So *add* or *remove* methods of List interface will result in *UnsupportedOperationException*.

### 9. Dates
Which of the following will print 2019-03-07?
```java
System.out.println(LocalDate.of(2019, 3, 7));        
System.out.println(LocalDate.of(2019, 4, 7));        
System.out.println(LocalDate.of(2019, Calendar.MARCH, 7)); 
System.out.println(LocalDate.of(2019, Month.MARCH, 7));    
```
First and fourth will. What happens here is the mix of Java 8 LocalDate and traditional Date. LocalDate starts calculating months from 1, while Date uses Calendar, which indexes months starting from zero. So Calendar.MARCH = 2 and LocalDate.of(2019,2,7) is February seventh. 

LocalDate also introduced some useful methods for date manipulation, like *plusDays*. Comfortably, it adds number of days to the date, even increasing a month/year if necessary. But the code below will fail, as same principles are not applied on object creation. Be specific, please.
```java
LocalDate date = LocalDate.of(2019, Month.FEBRUARY, 30); 
// java.time.DateTimeException: Invalid date 'FEBRUARY 30'
```

### 10. Default methods
Java 8 has introduces many interesting features, including default methods for interfaces. Useful or not, they might be a cause of a turmoil in your code. For example, let's declare two interfaces with the same default method:
```java
public interface JuniorProgrammer {
    default int getBugsPerSecond() {
        return 10;
    }
}
public interface SeniorProgrammer {
    default int getBugsPerSecond() {
        return 10;
    }
}
```
Not much of a problem, it might seem, as both programmers make 10 bugs per seconds, so interfaces might be used interchangeably. But what if a class implements them both?
```java
public class Programmer implements JuniorProgrammer, SeniorProgrammer {
}
```
Well, compiler will not allow it. Class might implement multiple interfaces, but they might not have default methods with the same method signature. Unless default method is overridden:
```java
public class Programmer implements JuniorProgrammer, SeniorProgrammer {
    public int getBugsPerSecond() {
        return 10;
    }
}
```
Compilation succeeds, but the default methods from interfaces are ignored, hence are pointless.

### Conclusion

OCA certification might be a daunting experience. This article provides some clarity on the trickiest parts of java language you might encounter during your examination or might have simply forgotten during long hours of writing perfect code with the help of your IDE. 

For further reference, I would recommend checking out an amazing book by Jeanne Boyarsky and Scott Selikoff ["OCA: Oracle Certified Associate Java SE 8 Programmer I Study Guide: Exam 1Z0-808"](https://www.goodreads.com/book/show/23059696-oca). It is not only a great content for the exam preparation, but is also perfect for refreshing some forgotten java knowledge.

Source code of the examples used in this article can be found at my github page: https://github.com/keizik/java-tricks