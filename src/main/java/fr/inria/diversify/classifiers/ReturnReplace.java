package fr.inria.diversify.classifiers;

import fr.inria.diversify.transformation.Transformation;
import fr.inria.diversify.transformation.ast.ASTReplace;
import spoon.reflect.code.CtReturn;
import spoon.reflect.declaration.CtElement;

/**
 * Created by marodrig on 24/10/2014.
 */
public class ReturnReplace extends TransformClassifier {
    @Override
    public boolean isUserFilter() {
        return false;
    }

    @Override
    protected boolean canClassify(Transformation transform) {
        if ( transform instanceof ASTReplace) {
            CtElement e = ((ASTReplace)transform).getTransplantationPoint().getCtCodeFragment();
            CtElement r = ((ASTReplace)transform).getTransplant().getCtCodeFragment();
            return hasElementOfType(e, CtReturn.class) || hasElementOfType(r, CtReturn.class);
        }
        return false;
    }

    @Override
    protected int calculateValue(Transformation transform) {
        return MEDIUM;
    }

    @Override
    public String getDescription() {
        return "Return replaced";
    }

    @Override
    public int getWeight() {
        return MEDIUM;
    }
}
