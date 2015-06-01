package fr.inria.diversify.classifiers;

import fr.inria.diversify.transformation.Transformation;
import fr.inria.diversify.transformation.ast.ASTReplace;
import spoon.reflect.code.CtThrow;
import spoon.reflect.declaration.CtElement;


/**
 * Gives negative weight to the transformations changing exceptions for exception.
 * <p/>
 * Exceptions substituted by another are probably never checked.
 * <p/>
 * Created by marodrig on 06/10/2014.
 */
public class ExceptionByException extends TransformClassifier {

    @Override
    public boolean isUserFilter() {
        return false;
    }

    @Override
    protected boolean canClassify(Transformation transplant) {
        //The sosie transformation is inside the Transplant UI representation
        return (transplant instanceof ASTReplace);
    }

    @Override
    protected int calculateValue(Transformation transplant) {
        ASTReplace replace = (ASTReplace) transplant;

        CtElement ctTP = replace.getTransplantationPoint().getCtCodeFragment();
        CtElement ctT = replace.getTransplant().getCtCodeFragment();
        //String spoonTP = transplant.getSpoonType();
        //String spoonT = transplant.getTransplantationPoint().getSpoonType();

        if ((ctTP instanceof CtThrow && ctT instanceof CtThrow)
                /*|| (spoonT.contains("CtThrow") && spoonTP.contains("CtThrow"))*/) {
            return getWeight();
        } else {
            return 0;
        }
    }

    @Override
    public String getDescription() {
        return "Exceptions substituted by another exception";
    }

    @Override
    public int getWeight() {
        return USELESS;
    }
}
