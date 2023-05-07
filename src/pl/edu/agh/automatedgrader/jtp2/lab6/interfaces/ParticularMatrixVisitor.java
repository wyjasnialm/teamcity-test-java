package pl.edu.agh.automatedgrader.jtp2.lab6.interfaces;

import pl.edu.agh.automatedgrader.jtp2.lab5.interfaces.DenseMatrix;
import pl.edu.agh.automatedgrader.jtp2.lab5.interfaces.SparseMatrix;

public interface ParticularMatrixVisitor extends MatrixVisitor
{
	void visit(DenseMatrix denseMatrix);

	void visit(SparseMatrix sparseMatrix);
}
