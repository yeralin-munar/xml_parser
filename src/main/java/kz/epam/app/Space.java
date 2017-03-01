package kz.epam.app;

import kz.epam.main.Galaxy;
import kz.epam.main.Universe;
import kz.epam.presentation.UniversePresentation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Yeralin Munar
 *         date: 3/1/17
 */
public class Space {
    private List<Universe> universes= new ArrayList<Universe>();

    public Space(){};

    public Space(ArrayList<Universe> universes){
        this.universes = universes;
    }

    public void setUniverses(List<Universe> universes) {
        this.universes = universes;
    }

    public List<Universe> getUniverses() {
        return universes;
    }

    @Override
    public boolean equals(Object o) {
        Space other = (Space) o;
        int thisSize = getUniverses().size();
        if (thisSize != other.getUniverses().size())
            return false;
        for (int i = 0; i < thisSize; i++) {
            Universe thisUniverse = this.getUniverses().get(i);
            Universe otherUniverse = other.getUniverses().get(i);

            if (!thisUniverse.equals(otherUniverse))
                return false;
        }
        return true;
    }

    @Override
    public String toString(){
        String universesString = "";

        for (Universe universe: universes){
            UniversePresentation universePresentation = new UniversePresentation(universe);
            universesString += universePresentation + "\n";
        }

        return universesString;
    }
}
