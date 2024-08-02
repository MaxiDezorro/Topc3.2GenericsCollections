import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import ru.netology.Comment
import ru.netology.Note
import ru.netology.NoteService
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class NoteServiceTest {

    @Before
    fun clearBeforeTest() {
        val noteService: NoteService = NoteService()
        noteService.clear()

    }


    @Test
    fun addNote() {
        val noteService: NoteService = NoteService()
        val result = noteService.add(
            Note(
                1,
                "Заметка",
                "Текст заметки",
            )
        )
        assertNotEquals(0, result.noteId)

    }

    @Test
    fun createComment() {
        val noteService: NoteService = NoteService()
        val note = noteService.add(
            Note(
                1,
                "Заметка",
                "Текст заметки",

                )
        )
        val result = noteService.createComment(
            note.noteId,
            Comment(
                7,
                "коментарий к заметке"
            )
        )
        assertEquals(7, result)
    }

    @Test
    fun getByIdPositive() {
        val noteService: NoteService = NoteService()
        val note = noteService.add(
            Note(
                9,
                "Заметка",
                "Текст заметки",

                )
        )
        val result = noteService.getById(9)
        assertEquals(9, result.noteId)
    }

    @Test(expected = Exception::class)
    fun getByIdNegative() {
        val noteService: NoteService = NoteService()
        val note = noteService.add(
            Note(
                9,
                "Заметка",
                "Текст заметки",

                )
        )
        val result = noteService.getById(4)
    }

    @Test
    fun deleteNote() {
        val noteService: NoteService = NoteService()
        val note = noteService.add(
            Note(
                2,
                "Заметка",
                "Текст заметки",

                )
        )
        val result = noteService.delete(2)
        assertTrue(result)
    }

    @Test
    fun deleteComment() {
        val noteService: NoteService = NoteService()
        val note = noteService.add(
            Note(
                1,
                "Заметка",
                "Текст заметки",

                )
        )
        val newComment = Comment(1, "коментарий")
        val comment = note.comments.add(newComment)
        val result = noteService.deleteComment(1,1)
        assertTrue(result)

    }

    @Test
    fun editNote() {
        val noteService: NoteService = NoteService()
        val note = noteService.add(
            Note(
                1,
                "Заметка",
                "Текст заметки",

                )
        )
        val noteUpdate = Note(
            1,
            "Обновленная заметка",
            "Обновленный текст заметки"
        )

        val  result = noteService.edit(1,noteUpdate)
        assertTrue(result)
    }


    @Test
    fun editComment() {
        val noteService: NoteService = NoteService()
        val note = noteService.add(
            Note(
                1,
                "Заметка",
                "Текст заметки",

                )
        )
        val newComment = Comment(1, "коментарий")
        val comment = note.comments.add(newComment)
        val editNewComment = Comment(1,"Обновленный коментарий")
        val result = noteService.editComment(1,1, editNewComment)
        assertTrue(result)

    }

    @Test
    fun getNotesList() {
        val noteService: NoteService = NoteService()
        val note = noteService.add(
            Note(
                1,
                "Заметка",
                "Текст заметки",

                )
        )
        val result = noteService.get()
        assertNotNull(result)
    }

    @Test
    fun getComments() {
        val noteService: NoteService = NoteService()
        val note = noteService.add(
            Note(
                1,
                "Заметка",
                "Текст заметки",

                )
        )
        val newComment = Comment(1, "коментарий")
        val comment = note.comments.add(newComment)
        val result = noteService.getComments(1)
        assertNotNull(result)
    }

    @Test
    fun restoreComment() {
        val noteService: NoteService = NoteService()
        val note = noteService.add(
            Note(
                1,
                "Заметка",
                "Текст заметки",

                )
        )
        val newComment = Comment(1, "коментарий")
        val comment = note.comments.add(newComment)
        val delete = noteService.deleteComment(1,1)
        val result = noteService.restoreComment(1,1)
        assertTrue(result)
    }
}