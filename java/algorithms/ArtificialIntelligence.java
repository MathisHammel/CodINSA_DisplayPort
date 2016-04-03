package algorithms;

import algorithms.behaviours.*;
import algorithms.pathfinders.FindPathExploration;
import algorithms.pathfinders.FindPathInterface;
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

        // evaluer la stratégie offensive ou defensive
        boolean offensive = true;
        DefensiveEvaluation defensiveEvaluation = new DefensiveEvaluation();
        if (defensiveEvaluation.evaluate(game) > 0.8) {
            behaviour = new BehaviourDefensive();
        }
        else{
            behaviour = new BehaviourOffensive();

        }
        System.out.println("Offensive ? " + offensive);

        // Detection de la taille et affectation des algorithmes
        int worldSize = game.getWorld().getSize();
        if (worldSize <= World.SIZE_SMALL){
            setup = new SetupSmall();
        }
        else if(worldSize <= World.SIZE_MEDIUM){
            setup = new SetupMedium();
        }
        else{
            setup = new SetupLarge();
        }

        Action set = setup.deploy(game);
        if(set != null) {
            actions.add(set);
        } else {
            // execution des algorithme communs et  du behaviour selecionné

            // Faire potentiellement construire les ingénieurs sur la pos courante
            // Todo

            actions.addAll(behaviour.decideActions(game));

            // Déplacement des Scouts
            FindPathInterface findPathScout = new FindPathExploration();
            actions.addAll(findPathScout.evaluatePath(game, null, null));
        }

        return actions;
    }
}
