package fr.inria.diversify.classifiers;

import fr.inria.diversify.transformation.Transformation;
import fr.inria.diversify.transformation.ast.ASTReplace;
import spoon.reflect.declaration.CtElement;

/**
 * Created by marodrig on 24/10/2014.
 */
public class FieldAssignmentReplace extends TransformClassifier {
    @Override
    public boolean isUserFilter() {
        return false;
    }

    @Override
    protected boolean canClassify(Transformation transform) {
        if ( transform instanceof ASTReplace) {
            CtElement e = ((ASTReplace)transform).getTransplant().getCtCodeFragment();
            CtElement r = ((ASTReplace)transform).getTransplantationPoint().getCtCodeFragment();
            return getFieldAssignments(r).size() > 0 || getFieldAssignments(e).size() > 0;
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
        return "Field assignment replaced";
    }

}
