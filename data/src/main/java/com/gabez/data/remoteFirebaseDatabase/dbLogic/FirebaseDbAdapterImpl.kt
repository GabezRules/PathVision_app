package com.gabez.data.remoteFirebaseDatabase.dbLogic

import com.gabez.data.remoteFirebaseDatabase.FirebaseDataHolder
import com.gabez.data.remoteFirebaseDatabase.entities.PathFirebaseEntity
import com.gabez.data.remoteFirebaseDatabase.entities.SkillFirebaseEntity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class FirebaseDbAdapterImpl(
    private val firebaseDatabase: FirebaseDatabase,
    private val auth: FirebaseAuth,
    private val dataHolder: FirebaseDataHolder
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

    override fun getRemotePaths() {
        auth.currentUser?.let { user ->
            firebaseDatabase.reference.child("users").child(user.uid).child("paths").get().addOnCompleteListener {
                task ->

                val allPaths: ArrayList<PathFirebaseEntity> = ArrayList()

                for(snap in task.result!!.children){
                    allPaths.add(snap.getValue(PathFirebaseEntity::class.java)!!)
                }

                dataHolder.restoreAllPaths(allPaths)
            }
        }
    }

    override fun getRemoteSkills() {
        auth.currentUser?.let { user ->
            firebaseDatabase.reference.child("users").child(user.uid).child("skills").get().addOnCompleteListener {
                    task ->

                val allSkills: ArrayList<SkillFirebaseEntity> = ArrayList()

                for(snap in task.result!!.children){
                    allSkills.add(snap.getValue(SkillFirebaseEntity::class.java)!!)
                }

                dataHolder.restoreAllSkills(allSkills)
            }
        }
    }
}