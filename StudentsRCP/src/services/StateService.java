package services;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.services.IEvaluationService;

/**
 *
 * The class is service stores the state of the form.
 * Also, the class is responsible for starting all the testers
 * that are responsible for the state of all buttons of the application.
 *
 * @author Lyska Lyudmila
 */
public class StateService{

    /** Service for changing state buttons on form and menu panel **/
    private static StateService _stateService;

    /** Service to run all testers from list **/
    private static final IEvaluationService _evaluationService = PlatformUI.getWorkbench()
            .getService(IEvaluationService.class);

    /** List stores id all testers **/
    private final List<String> _listTesters;

    static {
        _stateService = new StateService();
    }

    /** The state of the text fields from panel **/
    private StateForm _state = StateForm.EMPTY;

    private StateService() {
        _listTesters = new ArrayList<>();
        _listTesters.add("studentsrcp.deleteTester.isSelected");
        _listTesters.add("studentsrcp.tester.isEnabledState");
    }

    /**
     * Gets a link to itself.
     *
     * @return state service
     */
    public static StateService getInstance() {
        return _stateService;
    }

    /**
     * Sends event for all listeners.
     */
    public void runEvent() {
        // call all testers
        for (String string : _listTesters) {
            _evaluationService.requestEvaluation(string);
        }
    }

    /**
     * Gets state form form.
     *
     * @return state
     */
    public StateForm getState() {
        return _state;
    }

    /**
     * Sets state form.
     *
     * @param state
     */
    public void setState(StateForm state) {
        _state = state;
    }

    /**
     * Checks state is equal to state passed in arg.
     *
     * @param state for checking
     * @return true - if equal, false - if not
     */
    public boolean checkState(StateForm state) {
        return _state.equals(state);
    }

}