package ua.nure.lyubimtsev.summarytask4;

public class Person {

    private int age;
    private Gender sex;
    private String name;

    public Person(int age, Gender sex, String name) {
        this.age = age;
        this.sex = sex;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getSex() {
        return sex;
    }

    public void setSex(Gender sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", sex=" + sex +
                ", name='" + name + '\'' +
                '}';
    }

    enum Gender { MALE, FEMALE }
}
