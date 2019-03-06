package poi;

public abstract class CellValCallBack {
    /**
     * 设置cell值的时候回调
     * @param dadaRowIndex 第几行数据,从0开始
     * @param cellIndex 一行里面的第几个cell,从0开始
     * @return
     */
    abstract String getCellVal(int dadaRowIndex,int cellIndex);
}
