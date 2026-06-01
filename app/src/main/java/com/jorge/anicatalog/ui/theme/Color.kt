package com.jorge.anicatalog.ui.theme

import androidx.compose.ui.graphics.Color

// Roxo (EVA-01)
val VioletPrimary = Color(0xFF8B5CF6)
val VioletLight   = Color(0xFFA78BFA)
val VioletDark    = Color(0xFF6D28D9)

// Verde (plugue)
val GreenDone     = Color(0xFF10B981)
val GreenDoneBg   = Color(0xFF064E3B)
val GreenDoneBorder = Color(0xFF059669)

// Laranja (cabelo Asuka)
val AmberPaused   = Color(0xFFF97316)
val AmberBg       = Color(0xFF7C2D12)
val AmberBorder   = Color(0xFFEA580C)

// Neutros
val OledBlack     = Color(0xFF000000)
val White         = Color(0xFFFFFFFF)
val TextPrimary   = Color(0xFFF1F5F9)
val TextSecondary = Color(0xFFCBD5E1)
val TextMuted     = Color(0xFF94A3B8)
val TextDim       = Color(0xFF64748B)

// Superfícies e bordas
val SurfaceCard   = Color(0xFF1E1E2A)
val SurfaceHigh   = Color(0xFF2A2A3A)
val SurfaceBorder = Color(0xFF2D2D3F)

// Sinônimos para manter compatibilidade
val BlueLight     = VioletLight
val BluePrimary   = VioletPrimary
val BlueBg        = VioletDark.copy(alpha = 0.3f)
val BlueBorder    = VioletLight.copy(alpha = 0.5f)