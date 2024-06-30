package primitives;

public class Material {
    public Double3 Ks =Double3.ZERO;
    public Double3 Kd =Double3.ZERO;
    public int nShininess=0;

    public Material setKs(Double3 ks) {
        this.Ks = ks;
        return this;
    }

    public Material setKd(Double3 Kd) {
        this.Kd = Kd;
        return this;
    }


    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

    public Material setKs(double Ks) {
        this.Ks = new Double3(Ks);
        return this;
    }
    public Material setKd(double Kd) {
        this.Kd = new Double3(Kd);
        return this;
    }
}
