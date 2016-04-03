package algorithms;

import algorithms.behaviours.*;
import algorithms.setups.SetupInterface;
import algorithms.setups.SetupLarge;
import algorithms.setups.SetupMedium;
import algorithms.setups.SetupSmall;
import algorithms.worldevaluations.DefensiveEvaluation;
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
        boolean offensive = true;
        DefensiveEvaluation defensiveEvaluation = new DefensiveEvaluation();
        if (defensiveEvaluation.evaluate(game) > 0.8) {
            offensive = false;
        }
        System.out.println("Offensive ? " + offensive);

        // Detection de la taille et affectation des algorithmes
        int worldSize = game.getWorld().getSize();
        if (worldSize <= World.SIZE_SMALL){
            setup = new SetupSmall();
            if (offensive) {
                behaviour = new BehaviourOffensiveSmall();
            } else {
                behaviour = new BehaviourDefensiveSmall();
            }
        }
        else if(worldSize <= World.SIZE_MEDIUM){
            setup = new SetupMedium();
            if (offensive) {
                behaviour = new BehaviourOffensiveMedium();
            } else {
                behaviour = new BehaviourDefensiveMedium();
            }
        }
        else{
            setup = new SetupLarge();
            if (offensive) {
                behaviour = new BehaviourOffensiveLarge();
            } else {
                behaviour = new BehaviourDefensiveLarge();
            }
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
