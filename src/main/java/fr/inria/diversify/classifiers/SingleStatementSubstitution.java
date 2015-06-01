package fr.inria.diversify.classifiers;

import fr.inria.diversify.transformation.Transformation;
import fr.inria.diversify.transformation.ast.ASTReplace;
import spoon.reflect.code.CtLocalVariable;
import spoon.reflect.code.CtNewClass;
import spoon.reflect.declaration.CtElement;

/**
 * Gives a little bad weight to elements substituted by a single statement (excluding variable declaration and New Class)
 *
 * Created by marodrig on 13/10/2014.
 */
public class SingleStatementSubstitution extends StatementSubstitution {

    @Override
    public boolean isUserFilter() {
        return false;
    }

    @Override
    protected int calculateValue(Transformation transplant) {
        ASTReplace replace = (ASTReplace) transplant;

        CtElement cf = replace.getTransplant().getCtCodeFragment();

        if (cf.getElements(blockFilter).size() == 0 &&
                !(cf instanceof CtLocalVariable || cf instanceof CtNewClass)) {
            return getWeight();
        }

        return 0;
    }

    @Override
    public String getDescription() {
        return "Statement substitution";
    }

    @Override
    public int getWeight() {
        return MEDIUM;
    }
}
