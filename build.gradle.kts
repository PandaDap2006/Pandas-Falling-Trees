@file:Suppress("UnstableApiUsage")

plugins {
	alias(libs.plugins.kotlin.jvm) apply false
	alias(libs.plugins.kotlin.serialization) apply false
	alias(libs.plugins.shadow) apply false
	alias(libs.plugins.fabric.loom) apply false
	alias(libs.plugins.neoforge.moddev) apply false
	alias(libs.plugins.publish.mod) apply false
	alias(libs.plugins.ksp) apply false
}


allprojects {
	group = "dev.pandasystems"
	version = "1.0.0-SNAPSHOT"
}