package fr.inria.diversify.classifiers;

import fr.inria.diversify.transformation.Transformation;
import fr.inria.diversify.transformation.ast.ASTDelete;
import spoon.reflect.declaration.CtElement;

/**
 * Created by marodrig on 24/10/2014.
 */
public class InnocuousMethodCallDelete extends TransformClassifier {
    @Override
    public boolean isUserFilter() {
        return false;
    }

    @Override
    protected boolean canClassify(Transformation transform) {
        if (transform instanceof ASTDelete) {
            CtElement e = ((ASTDelete) transform).getTransplantationPoint().getCtCodeFragment();
            return containsInnocuousInvocation(e);
            /*
            if (transform.getContainsInnocuousCalls() == null) {
                boolean b = containsInnocuousInvocation(e);
                transform.setContainsInnocuousCalls(b);
            } else {
                return transform.getContainsInnocuousCalls();
            }*/
        }
        return false;
    }

    @Override
    protected int calculateValue(Transformation transform) {
        return getWeight();
    }

    @Override
    public int getWeight() {
        return STRONG;
    }

    @Override
    public String getDescription() {
        return "Innocuous method call deleted";
    }

}
