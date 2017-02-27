package commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import services.StateService;
import services.TableService;
import services.TableServiceImp;

public class DeleteHandler extends AbstractHandler {

    /** Service for working with data in table **/
    private final TableService _service = TableServiceImp.getInstance();

    /** Service for handling event on form **/
    private final StateService _stateService = StateService.getInstance();

    /**
     * Processes event pressing the "Delete" button.
     *
     * Gives instructions the table of service to remove a selected row. Gives instructions the state service to disable
     * "Delete" button.
     */
    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        _service.removeSelectStudent();
        _stateService.runEvent();

//        // to call tester
//        IEvaluationService evaluationService = PlatformUI.getWorkbench().getService(IEvaluationService.class);
//        evaluationService.requestEvaluation("studentsrcp.deleteTester.isSelected");
        return null;
    }

}
