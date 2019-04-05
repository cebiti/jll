public class CompInteger implements ComparatorCounter {
    
    private boolean countFeature;
    private int	counts;
    
    public CompInteger() {
    	countFeature = false;
    	counts = 0;
    }
    
    public void enableCount() {
    	countFeature = true;
    }
    
    public void disableCount() {
    	countFeature = false;
    }
    
    public int getCount() {
    	return counts;
    }
    
    public void setCount(int a) {
    	counts = a;
    }
    
    public void rstCount() {
    	setCount(0);
    }
    
    public boolean isEqualTo (Object a, Object b) throws NonComparableException {
        try {
            Integer ai = (Integer) a;
            Integer bi = (Integer) b;
     		counts++;       
            return ai.intValue() == bi.intValue();
        } catch (ClassCastException e) {throw new NonComparableException(); }
    }
    
    public boolean isGreaterThan (Object a, Object b) throws NonComparableException {
        try {
            Integer ai = (Integer) a;
            Integer bi = (Integer) b;
            counts++;       
            return ai.intValue() > bi.intValue();
        } catch (ClassCastException e) {throw new NonComparableException(); }
    }
    
    public boolean isLessThan (Object a, Object b) throws NonComparableException {
        try {
            Integer ai = (Integer) a;
            Integer bi = (Integer) b;
            counts++;       
            return ai.intValue() < bi.intValue();
        } catch (ClassCastException e) {throw new NonComparableException(); }
    }
    
    public boolean isGreaterThanOrEqualTo (Object a, Object b) throws NonComparableException {
        try {
            Integer ai = (Integer) a;
            Integer bi = (Integer) b;
            counts++;       
            return ai.intValue() >= bi.intValue();
        } catch (ClassCastException e) {throw new NonComparableException(); }
    }
    
    public boolean isLessThanOrEqualTo (Object a, Object b) throws NonComparableException {
        try {
            Integer ai = (Integer) a;
            Integer bi = (Integer) b;
            counts++;       
            return ai.intValue() <= bi.intValue();
        } catch (ClassCastException e) {throw new NonComparableException(); }
    }
    
    public boolean isComparable (Object a) {
    	try {
    		return (a instanceof Integer);
    	} catch (Exception e) {
    		return false;
    	}
    }
}

