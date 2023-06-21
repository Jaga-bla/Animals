package pibd.jagoda.animals.model;

public abstract class Animal extends Subject {
    protected static int indexCount = 0;
    protected int index;
    protected Owner owner;

    public Animal(Owner owner, String name, Sex sex, int age) {
        super(name, sex, age);
        this.owner = owner;
        this.index = ++indexCount;
    }
    public static int getIndexCount() {
        return indexCount;
    }

    public static void setIndexCount(int indexCount) {
        Animal.indexCount = indexCount;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
