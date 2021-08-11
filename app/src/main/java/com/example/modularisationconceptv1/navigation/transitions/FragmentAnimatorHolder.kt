package com.example.modularisationconceptv1.navigation.transitions

/**
 * Holder for animation resources to run for the fragments that are
 * entering and exiting in this transaction.
 *
 * @param enter An animation or animator resource ID used for the enter animation on the
 *              view of the fragment being added or attached.
 * @param exit An animation or animator resource ID used for the exit animation on the
 *             view of the fragment being removed or detached.
 * @param popEnter An animation or animator resource ID used for the enter animation on the
 *                 view of the fragment being readded or reattached caused by
 *                 {@link FragmentManager#popBackStack()} or similar methods.
 * @param popExit An animation or animator resource ID used for the enter animation on the
 *                view of the fragment being removed or detached caused by
 *                {@link FragmentManager#popBackStack()} or similar methods.
 */
data class FragmentAnimatorHolder(
    val enter: Int,
    val exit: Int,
    val popEnter: Int,
    val popExit: Int,
)

val DefaultFragmentAnimatorHolder = FragmentTransitionType.Fade.toFragmentAnimatorHolder()