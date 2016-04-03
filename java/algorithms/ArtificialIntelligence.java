package algorithms;

import models.World;
import rules.Action;
import models.Game;

import java.util.ArrayList;
import java.util.List;

public class ArtificialIntelligence {
    public static Action getNextAction(Game game) {
        return null;
    }

    public static List<Action> getNextActions(Game game) {
        // Calcul des score de chaques cases
        game.getWorld().calculateScores();

        List<Action> actions = new ArrayList<>();
        // Algorithms
        SetupInterface setup;
        BehaviourInterface behaviour;

        // evaluer la stratégie


        // Detection de la taille et affectation des algorithmes
        int worldSize = game.getWorld().getSize();
        if (worldSize <= World.SIZE_SMALL){
            setup = new SetupSmall();
            // TODO à changer
            behaviour = new BehaviourOffensiveMedium();
        }
        else if(worldSize <= World.SIZE_MEDIUM){
            setup = new SetupMedium();
            // TODO à changer
            behaviour = new BehaviourOffensiveMedium();
        }
        else{
            // World.SIZE_LARGE
            // TODO SetupLarge()
            setup = new SetupMedium();
            // TODO à changer
            behaviour = new BehaviourOffensiveMedium();
        }

        Action set = setup.deploy(game);
        if(set != null) {
            actions.add(set);
        } else {
            actions.addAll(behaviour.decideActions(game));
        }

        return actions;
    }
}
