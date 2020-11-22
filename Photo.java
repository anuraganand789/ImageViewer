public record Photo(String location, String title){
    
    public void print(){
        System.out.println(this.toString());
    }
    
    @Override
    public String toString(){
        return "Location " + location + ", Title " + title;
    }

};
