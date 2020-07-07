public class SubA {
    private Integer seq =999;
    private String n1;
    private String n2;

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getN1() {
        return n1;
    }

    public void setN1(String n1) {
        this.n1 = n1;
    }

    public String getN2() {
        return n2;
    }

    public void setN2(String n2) {
        this.n2 = n2;
    }

    @Override
    public String toString() {
        return "SubA{" +
                "seq=" + seq +
                ", n1='" + n1 + '\'' +
                ", n2='" + n2 + '\'' +
                '}';
    }
}
