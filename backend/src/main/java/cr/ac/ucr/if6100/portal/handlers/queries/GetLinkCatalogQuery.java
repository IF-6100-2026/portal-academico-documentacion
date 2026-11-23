package cr.ac.ucr.if6100.portal.handlers.queries;

import cr.ac.ucr.if6100.portal.handlers.commands.ManageGuardianLinkHandler.LinkView;
import java.util.List;
import java.util.UUID;

public interface GetLinkCatalogQuery {
    Result query(String actorUsername);

    sealed interface Result {
        record Success(List<GuardianOption> guardians, List<StudentOption> students,
            List<LinkView> links) implements Result { }
        record Forbidden() implements Result { }
    }

    record GuardianOption(String username, String displayName) { }
    record StudentOption(UUID id, String fullName, String gradeLevel, String groupName) { }
}
