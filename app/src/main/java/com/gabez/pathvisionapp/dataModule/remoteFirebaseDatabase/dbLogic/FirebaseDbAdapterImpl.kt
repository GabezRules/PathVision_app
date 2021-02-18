package com.gabez.pathvisionapp.dataModule.remoteFirebaseDatabase.dbLogic

import com.gabez.pathvisionapp.dataModule.remoteFirebaseDatabase.entities.PathFirebaseEntity
import com.gabez.pathvisionapp.dataModule.remoteFirebaseDatabase.entities.SkillFirebaseEntity
import com.gabez.pathvisionapp.app.domain.entities.PathObject
import com.gabez.pathvisionapp.app.domain.entities.SkillObject
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

@ExperimentalCoroutinesApi
class FirebaseDbAdapterImpl: FirebaseDbAdapter {

    private val firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun addPath(path: PathFirebaseEntity) {
        auth.currentUser?.let { user ->
            firebaseDatabase.reference.child("users").child(user.uid).child("paths")
                .child(path.title)
                .get().addOnCompleteListener { task ->
                    if (task.result == null) {
                        firebaseDatabase.reference.child("users").child(auth.currentUser!!.uid)
                            .child(path.title).setValue(path)
                    }
                }
        }
    }

    override fun deletePath(name: String) {
        auth.currentUser?.let { user ->
            firebaseDatabase.reference.child("users").child(user.uid).child("paths").child(name)
                .get().addOnCompleteListener { task ->
                    if (task.result != null) {
                        firebaseDatabase.reference.child("users").child(user.uid).child(name)
                            .removeValue()
                    }
                }
        }
    }

    override fun addSkill(skill: SkillFirebaseEntity) {
        auth.currentUser?.let { user ->
            firebaseDatabase.reference.child("users").child(user.uid).child("skills")
                .child(skill.title)
                .get().addOnCompleteListener { task ->
                    if (task.result == null) {
                        firebaseDatabase.reference.child("users").child(user.uid).child("skills")
                            .child(skill.title).setValue(skill)
                    }
                }
        }
    }

    override fun deleteSkill(skill: SkillFirebaseEntity) {
        auth.currentUser?.let { user ->
            firebaseDatabase.reference.child("users").child(user.uid).child("skills")
                .child(skill.title)
                .get().addOnCompleteListener { task ->
                    if (task.result != null) {
                        firebaseDatabase.reference.child("users").child(user.uid).child("skills")
                            .child(skill.title).removeValue()
                    }
                }
        }
    }

    override fun updateSkillStatus(skill: SkillFirebaseEntity) {
        auth.currentUser?.let { user ->
            firebaseDatabase.reference.child("users").child(user.uid).child("skills")
                .child(skill.title)
                .get().addOnCompleteListener { task ->
                    if (task.result != null) {
                        firebaseDatabase.reference.child("users").child(user.uid).child("skills")
                            .child(skill.title).setValue(skill)
                    }
                }
        }
    }

    override fun getRemotePaths(): Flow<PathObject> = callbackFlow {
        auth.currentUser?.let { user ->
            awaitClose {
                firebaseDatabase.reference.child("users").child(user.uid).child("paths").get()
                    .addOnCompleteListener {

                            task ->

                        for (snap in task.result!!.children) {
                            offer(snap.getValue(PathFirebaseEntity::class.java)!!.toPathObject())
                        }
                    }
            }

        }
    }

    override fun getRemoteSkills(): Flow<SkillObject> = callbackFlow {
        auth.currentUser?.let { user ->
            awaitClose {
                firebaseDatabase.reference.child("users").child(user.uid).child("skills").get()
                    .addOnCompleteListener { task ->
                        for (snap in task.result!!.children) {
                            offer(snap.getValue(SkillFirebaseEntity::class.java)!!.toSkillObject())
                        }
                    }
            }

        }
    }
}