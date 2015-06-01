package fr.inria.diversify.classifiers;

import fr.inria.diversify.transformation.Transformation;
import fr.inria.diversify.transformation.ast.ASTReplace;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.visitor.filter.TypeFilter;

/**
 * Gives a bad weight to Transplant being a Statement (Not block, not var declaration) substitution .
 * <p/>
 * Created by marodrig on 07/10/2014.
 */
public abstract class StatementSubstitution extends TransformClassifier {

    protected static TypeFilter blockFilter = new TypeFilter(CtMethod.class);

    @Override
    protected boolean canClassify(Transformation transplant) {
        //The sosie transformation is inside the Transplant UI representation
        return (transplant instanceof ASTReplace);
    }
}
