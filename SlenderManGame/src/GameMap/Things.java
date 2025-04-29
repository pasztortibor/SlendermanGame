package GameMap;

/**
 * Tereptárgyak osztálya. Azokat azonosítja, illetve dönt arról, hogy az adott tereptárgy kódja alapján akadály-e.
 */
public class Things {

    boolean stepable;
    char code;

    public boolean isStepable() {
        return stepable;
    }

    public void setStepable(boolean stepable) {
        this.stepable = stepable;
    }

    public char getCode() {
        return code;
    }

    public void setCode(char code) {
        this.code = code;
        if(code == '0') //fu
            setStepable(true);
        else if(code == '1') //kis fa
            setStepable(true);
        else if(code == '2' || code == '3' || code == '4') //nagy fák
            setStepable(true);
        else if(code == '5') //haz
            setStepable(false);
        else if(code == '6' || code == '7') //autok
            setStepable(false);
        else if(code == '8') //teherauto
            setStepable(false);
        else if(code == '9' || code == 'a') //sziklak
            setStepable(false);
        else if(code == 'b') //hordo
            setStepable(false);
        else if(code == 'c') //padlo
            setStepable(true);
        else if(code == 'd') //bejarat
            setStepable(true);
    }
}