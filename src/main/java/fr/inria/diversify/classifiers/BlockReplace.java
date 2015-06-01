package fr.inria.diversify.classifiers;

import fr.inria.diversify.transformation.Transformation;
import fr.inria.diversify.transformation.ast.ASTReplace;
import spoon.reflect.code.CtBlock;
import spoon.reflect.declaration.CtElement;

/**
 * Created by marodrig on 24/10/2014.
 */
public class BlockReplace extends TransformClassifier {
    @Override
    public boolean isUserFilter() {
        return false;
    }

    @Override
    protected boolean canClassify(Transformation transform) {
        if ( transform instanceof ASTReplace) {
            CtElement e = ((ASTReplace)transform).getTransplantationPoint().getCtCodeFragment();
            CtElement r = ((ASTReplace)transform).getTransplant().getCtCodeFragment();
            return hasElementOfType(e, CtBlock.class) || hasElementOfType(r, CtBlock.class);
        }
        return false;
    }

    @Override
    protected int calculateValue(Transformation transform) {
        return MEDIUM;
    }

    @Override
    public String getDescription() {
        return "Block replaced";
    }

    @Override
    public int getWeight() {
        return MEDIUM;
    }
}
