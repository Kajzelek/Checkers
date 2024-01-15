public enum SessionVariable {
    myID(3);

    private int value;

    private SessionVariable(int var3) {
        this.setValue(var3);
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int var1) {
        this.value = var1;
    }
}
