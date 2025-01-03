package software.ulpgc.imageviewer.control;

public class PreviousCommand implements Command {
    private final ImagePresenter presenter;

    public PreviousCommand( ImagePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void execute() {
        presenter.show(presenter.getCurrentImage().previous());
    }
}
