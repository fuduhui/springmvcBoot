package poi;

public class Student {
    private String name;
    private Integer height;
    private String remark;

    public Student(String name,Integer height,String remark){
        this.name=name;
        this.height=height;
        this.remark=remark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
