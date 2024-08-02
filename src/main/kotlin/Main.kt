package ru.netology

class NoteService {
    private var notes: MutableList<Note> = mutableListOf()

    fun clear() {
        notes.clear()
    }


    //    содает новую заметку
    fun add(note: Note): Note {
        notes.add(note)
        return note
    }

    //    создает коментарий к заметке
    fun createComment(noteId: Int, comment: Comment): Int {
        val note = getById(noteId)
        if (note.noteId == noteId) {
            val newComment = comment
            note.comments.add(newComment)
            return comment.id
        } else {
            throw Exception("Заметки с таким id $noteId нет")
        }

    }

    //    удаляет заметку
    fun delete(noteId: Int): Boolean {
        val note = getById(noteId)
        return when {
            note.noteId == noteId -> notes.remove(note)
            else -> false
        }

    }

    //    удаляет коментарий к заметке
    fun deleteComment(noteId: Int, commentId: Int): Boolean {
        val note = getById(noteId)
        if (note.noteId == noteId) {
            val comment = note.comments.find { it.id == commentId }
            if (comment != null) {
                comment.delete = true
                return true
            }
            return false
        }
        return false
    }

    //    редактирует заметку
    fun edit(noteId: Int, updatedNote: Note): Boolean {
        val note = getById(noteId)
        if (note.noteId == noteId) {
            note.title = updatedNote.title
            note.text = updatedNote.text
            return true
        }
        return false
    }

    //    редактирует коментарий к заметке
    fun editComment(noteId: Int, commentId: Int, updatedComment: Comment): Boolean {
        val note = getById(noteId)
        if (note.noteId == noteId) {
            val comment = note.comments.find { it.id == commentId }
            if (comment != null && !comment.delete) {
                comment.content = updatedComment.content
                return true
            }
            return false
        }
        return false
    }

    //    возвращает список заметок
    fun get(): List<Note> {
        return notes
    }

    //    возвращает заметку по id
    fun getById(noteId: Int): Note {
        return notes.find { it.noteId == noteId }
            ?: throw Exception("Заметка с таким id $noteId не найдена ")

    }

    //    возвращает список комментариев к заметке
    fun getComments(noteId: Int): List<Comment> {
        val note = getById(noteId)
        if (note.noteId == noteId) {
            return note.comments.filter { it.delete != true }
        } else {
            throw Exception("Не возможно вернуть список коментариев. Заметка с таким id $noteId не найдена")
        }
    }

    //    восстанавливает удаленный коментарий
    fun restoreComment(noteId: Int, commentId: Int): Boolean {
        val note = getById(noteId)
        if (note.noteId == noteId) {
            val comment = note.comments.find { it.id == commentId }
            if (comment != null) {
                comment.delete = false
                return true
            }
            return false
        }
        return false
    }

}

data class Note(
    val noteId: Int , //идентефикатор заметки
    var title: String,
    var text: String,
    val comments: MutableList<Comment> = mutableListOf()
)

data class Comment(
    val id: Int,
    var content: String,
    var delete: Boolean = false // идентификатор удаления коментария
)

fun main() {
    val note: NoteService = NoteService()
    note.add(Note(1, "Заголовок заметки", "заметка"))
    note.add(Note(2, "Заголовок2", "заметка2"))


//    println(note.getById(2))
//    println()
//    println(note.get())
//    println()
    note.createComment(2, Comment(1, "коментарий к заметке"))
    note.createComment(2, Comment(2, "коментарий2"))
//    println(note.getById(2))
    println()
//    println(note.getComments(2))
//    note.delete(1)
    println(note.get())
//    println()
    note.deleteComment(2, 2)
//    println(note.getComments(2))
//    note.edit(1, Note(1, "Обновленный заголовок", "Обновленная заметка"))
    println(note.get())
    println()
//    note.editComment(2,2,Comment(1,"отлично"))
   println( note.getComments(2))
//    note.restoreComment(2,2)
//    note.editComment(2,2,Comment(1,"отлично"))
//    note.editComment(2,1, Comment(1, "Обновленный коментарий"))
//    println(note.getComments(2))
    println()
//    note.restoreComment(2,2)
    println(note.get())

}

