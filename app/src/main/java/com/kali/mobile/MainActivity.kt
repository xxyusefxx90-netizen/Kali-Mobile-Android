package com.kali.mobile

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kali.mobile.ui.theme.KaliMobileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KaliMobileTheme {
                KaliMobileApp()
            }
        }
    }
}

@Composable
fun KaliMobileApp() {
    val tools = listOf(
        ToolCard(
            title = "Network Scanner",
            description = "Scan ports, detect services, enumerate devices, and map the local environment.",
            status = "Ready"
        ),
        ToolCard(
            title = "Wireless Analyzer",
            description = "Inspect Wi-Fi networks, monitor signal strength, and capture wireless metadata.",
            status = "Ready"
        ),
        ToolCard(
            title = "Web Recon",
            description = "Audit HTTP services, test payloads, and inspect applications for web vulnerabilities.",
            status = "Ready"
        ),
        ToolCard(
            title = "Password Lab",
            description = "Prepare offline cracking workflows, dictionaries, and authentication stress tests.",
            status = "Ready"
        ),
        ToolCard(
            title = "Reporting",
            description = "Save session logs, export results, and produce structured security reports.",
            status = "Ready"
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Kali Mobile Pro",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFEAFEF5)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF0B1D16),
                    titleContentColor = Color(0xFFEAFEF5)
                )
            )
        },
        containerColor = Color(0xFF09160F)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFF09160F))
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            DashboardSummary()

            LazyColumn(
                contentPadding = PaddingValues(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(tools) { tool ->
                    ToolCardItem(tool = tool)
                }
            }
        }
    }
}

@Composable
private fun DashboardSummary() {
    Surface(
        shape = RoundedCornerShape(20.dp),
        color = Color(0xFF112B22),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
    ) {
        Column(
            modifier = Modifier.padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = "Advanced Penetration Testing Suite",
                style = MaterialTheme.typography.titleLarge,
                color = Color(0xFFEAFEF5),
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "This project provides a secure mobile toolkit framework for network analysis, reconnaissance, vulnerability assessment, and reporting.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFFB6DCC9)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                SummaryChip(label = "Tools", value = "05")
                SummaryChip(label = "Status", value = "Live")
                SummaryChip(label = "Mode", value = "Lab")
            }
        }
    }
}

@Composable
private fun SummaryChip(label: String, value: String) {
    Box(
        modifier = Modifier
            .weight(1f)
            .height(68.dp)
            .background(Color(0xFF153729), RoundedCornerShape(16.dp)),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = value,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF9BF5C8)
            )
            Text(
                text = label,
                fontSize = 12.sp,
                color = Color(0xFFB6DCC9)
            )
        }
    }
}

@Composable
private fun ToolCardItem(tool: ToolCard) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF122A20)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = tool.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color(0xFFEAFEF5),
                    fontWeight = FontWeight.Bold
                )

                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = Color(0xFF1D8C67)
                ) {
                    Text(
                        text = tool.status,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                        color = Color(0xFFECFFF4),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Text(
                text = tool.description,
                color = Color(0xFFB6DCC9),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
