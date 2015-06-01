package fr.inria.diversify.classifiers;

import fr.inria.diversify.transformation.Transformation;
import fr.inria.diversify.transformation.ast.ASTReplace;
import spoon.reflect.code.CtLocalVariable;
import spoon.reflect.code.CtNewClass;
import spoon.reflect.declaration.CtElement;

/**
 * Gives a bad weight to Transplant declaring a local variable
 * <p/>
 * These bad weight are given because most probably the transplant has no importance at all
 * <p/>
 * Created by marodrig on 07/10/2014.
 */
public class LocalVarDeclaration extends TransformClassifier {

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

        CtElement cf = replace.getTransplant().getCtCodeFragment();

        //CtNewClass example : Map<String, Int> a = new HashMap<String, Int>();
        if (cf instanceof CtLocalVariable || cf instanceof CtNewClass) {
            return getWeight();
        }

        return 0;
    }

    @Override
    public String getDescription() {
        return "Transplant is var declaration. Parent has delete.";
    }

    @Override
    public int getWeight() {
        return WEAK;
    }
}
