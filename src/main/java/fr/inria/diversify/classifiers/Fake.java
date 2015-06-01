package fr.inria.diversify.classifiers;

import fr.inria.diversify.transformation.Transformation;
import fr.inria.diversify.transformation.ast.ASTReplace;

/**
 * Created by marodrig on 27/10/2014.
 */
public class Fake extends TransformClassifier {
    @Override
    public boolean isUserFilter() {
        return false;
    }

    @Override
    protected boolean canClassify(Transformation transform) {
        if (transform.getType().contains("replace")) {
            ASTReplace replace = (ASTReplace)transform;
            return replace.getTransplant().equals(replace.getTransplant());
        }
        return false;
    }

    @Override
    protected int calculateValue(Transformation transform) {
        return getWeight();
    }

    @Override
    public String getDescription() {
        return "Fake replace (TP == Transplant)";
    }

    @Override
    public int getWeight() {
        return PLAIN_BAD;
    }
}
