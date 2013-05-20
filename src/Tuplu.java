

/**
 *
 * @author Gabriel Budau, Alina Midoschi Livia, Malaiasi Laura Georgiana, Turbatu Elena, Horia Constantin
 */
class Tuplu <T, U>{
    T x;
    U y;
    Tuplu(){
        x = null;
        y = null;
    }
    Tuplu(T _x, U _y){
        x = _x;
        y = _y;
    }
    public T getX(){
        return this.x;
    }
    public U getY(){
        return this.y;
    }
    
    public void setX(T _x){
        x = _x;
    }
    
    public void setY(U _y){
        y = _y;
    }
}
