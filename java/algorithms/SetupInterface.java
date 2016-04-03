
package algorithms;

import models.Game;
import rules.Action;

public interface SetupInterface {
    /**
     * Déploie le setup sur la map 
     * @param game L'état actuel du plateau
     * @return Actions à effectuer ou null si déjà déployé
     */
    Action deploy(Game game);
}
