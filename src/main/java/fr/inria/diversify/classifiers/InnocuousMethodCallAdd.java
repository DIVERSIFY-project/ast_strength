package fr.inria.diversify.classifiers;

import fr.inria.diversify.transformation.Transformation;
import fr.inria.diversify.transformation.ast.ASTAdd;
import spoon.reflect.declaration.CtElement;

/**
 * Created by marodrig on 24/10/2014.
 */
public class InnocuousMethodCallAdd extends TransformClassifier {
    @Override
    public boolean isUserFilter() {
        return false;
    }

    @Override
    protected boolean canClassify(Transformation transform) {
        if (transform instanceof ASTAdd) {
            CtElement e = ((ASTAdd) transform).getTransplantationPoint().getCtCodeFragment();
            CtElement r = ((ASTAdd) transform).getTransplant().getCtCodeFragment();
            return containsInnocuousInvocation(e) || containsInnocuousInvocation(r);
        }
        return false;
    }

    @Override
    protected int calculateValue(Transformation transform) {
        return getWeight();
    }

    @Override
    public int getWeight() {
        return MEDIUM;
    }

    @Override
    public String getDescription() {
        return "Innocuous method call added";
    }

}
