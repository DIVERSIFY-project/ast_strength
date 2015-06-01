package fr.inria.diversify.classifiers;

import fr.inria.diversify.transformation.Transformation;
import fr.inria.diversify.transformation.ast.ASTDelete;
import spoon.reflect.code.CtBlock;
import spoon.reflect.declaration.CtElement;

/**
 * Created by marodrig on 24/10/2014.
 */
public class BlockDelete extends TransformClassifier {
    @Override
    public boolean isUserFilter() {
        return false;
    }

    @Override
    protected boolean canClassify(Transformation transform) {
        if ( transform instanceof ASTDelete) {
            CtElement e = ((ASTDelete)transform).getTransplantationPoint().getCtCodeFragment();
            return hasElementOfType(e, CtBlock.class);
        }
        return false;
    }

    @Override
    protected int calculateValue(Transformation transform) {
        return MEDIUM;
    }

    @Override
    public String getDescription() {
        return "Block deleted";
    }

    @Override
    public int getWeight() {
        return MEDIUM;
    }
}
