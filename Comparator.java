public interface Comparator {

    public boolean isEqualTo (Object a, Object b) throws NonComparableException;

    public boolean isGreaterThan (Object a, Object b) throws NonComparableException;

    public boolean isLessThan (Object a, Object b) throws NonComparableException;

    public boolean isGreaterThanOrEqualTo (Object a, Object b) throws NonComparableException;

    public boolean isLessThanOrEqualTo (Object a, Object b) throws NonComparableException;

    public boolean isComparable (Object a);

}

