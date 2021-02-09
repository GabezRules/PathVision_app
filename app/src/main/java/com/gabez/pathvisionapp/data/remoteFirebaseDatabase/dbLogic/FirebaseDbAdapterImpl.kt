package com.gabez.pathvisionapp.data.remoteFirebaseDatabase.dbLogic

import com.gabez.pathvisionapp.data.remoteFirebaseDatabase.entities.PathFirebaseEntity
import com.gabez.pathvisionapp.data.remoteFirebaseDatabase.entities.SkillFirebaseEntity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.Flow

class FirebaseDbAdapterImpl(
    private val firebaseDatabase: FirebaseDatabase,
    private val auth: FirebaseAuth
) : FirebaseDbAdapter {

    override fun addPath(path: PathFirebaseEntity) {
        auth.currentUser?.let { user ->
            firebaseDatabase.reference.child("users").child(user.uid).child("paths").child(path.name)
                .get().addOnCompleteListener { task ->
                    if (task.result == null) {
                        firebaseDatabase.reference.child("users").child(auth.currentUser!!.uid)
                            .child(path.name).setValue(path)
                    }
                }
        }
    }

    override fun deletePath(name: String) {
        auth.currentUser?.let { user ->
            firebaseDatabase.reference.child("users").child(user.uid).child("paths").child(name)
                .get().addOnCompleteListener { task ->
                    if (task.result != null) {
                        firebaseDatabase.reference.child("users").child(user.uid).child(name).removeValue()
                    }
                }
        }
    }

    override fun addSkill(skill: SkillFirebaseEntity) {
        auth.currentUser?.let{ user ->
            firebaseDatabase.reference.child("users").child(user.uid).child("skills").child(skill.name)
                .get().addOnCompleteListener { task ->
                if(task.result == null){
                    firebaseDatabase.reference.child("users").child(user.uid).child("skills").child(skill.name).setValue(skill)
                }
            }
        }
    }

    override fun removeSkill(skill: SkillFirebaseEntity) {
        auth.currentUser?.let{ user ->
            firebaseDatabase.reference.child("users").child(user.uid).child("skills").child(skill.name)
                .get().addOnCompleteListener { task ->
                    if(task.result != null){
                        firebaseDatabase.reference.child("users").child(user.uid).child("skills").child(skill.name).removeValue()
                    }
                }
        }
    }

    override fun updateSkillStatus(skill: SkillFirebaseEntity) {
        auth.currentUser?.let{ user ->
            firebaseDatabase.reference.child("users").child(user.uid).child("skills").child(skill.name)
                .get().addOnCompleteListener { task ->
                    if(task.result != null){
                        firebaseDatabase.reference.child("users").child(user.uid).child("skills").child(skill.name).setValue(skill)
                    }
                }
        }
    }

    override fun getRemotePaths(): Flow<List<PathFirebaseEntity>> {
        TODO("Not yet implemented")
    }

    override fun getRemoteSkills(): Flow<List<SkillFirebaseEntity>> {
        TODO("Not yet implemented")
    }
}