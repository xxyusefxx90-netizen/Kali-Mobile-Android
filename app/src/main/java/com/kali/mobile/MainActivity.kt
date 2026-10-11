package com.kali.mobile

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
            description = "Port scan, service discovery and local mapping.",
            status = "Ready"
        ),
        ToolCard(
            title = "WiFi Analyzer",
            description = "Inspect nearby access points and signal quality.",
            status = "Ready"
        ),
        ToolCard(
            title = "Web Recon",
            description = "Audit endpoints and test payloads against targets.",
            status = "Ready"
        ),
        ToolCard(
            title = "Password Lab",
            description = "Prepare wordlists, dictionaries and offline testing.",
            status = "Ready"
        ),
        ToolCard(
            title = "Reporting",
            description = "Generate logs, export findings and share incident notes.",
            status = "Ready"
        )
    )

    var selectedTab by remember { mutableStateOf("Dashboard") }

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
                    containerColor = Color(0xFF0A1C16),
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
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TopTabs(selectedTab = selectedTab, onTabSelected = { selectedTab = it })

            when (selectedTab) {
                "Dashboard" -> DashboardContent(tools)
                "Network" -> NetworkContent()
                "WiFi" -> WifiContent()
                "Reports" -> ReportsContent()
                else -> DashboardContent(tools)
            }
        }
    }
}

@Composable
private fun TopTabs(selectedTab: String, onTabSelected: (String) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        listOf("Dashboard", "Network", "WiFi", "Reports").forEach { tab ->
            val selected = selectedTab == tab
            Button(
                onClick = { onTabSelected(tab) },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selected) Color(0xFF1B8F66) else Color(0xFF122A20),
                    contentColor = Color(0xFFEAFEF5)
                )
            ) {
                Text(text = tab, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

@Composable
private fun DashboardContent(tools: List<ToolCard>) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        DashboardSummary()

        LazyColumn(
            contentPadding = PaddingValues(vertical = 4.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(tools) { tool ->
                ToolCardItem(tool = tool)
            }
        }
    }
}

@Composable
private fun DashboardSummary() {
    Surface(
        shape = RoundedCornerShape(22.dp),
        color = Color(0xFF122A20),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Advanced Penetration Testing Suite",
                style = MaterialTheme.typography.titleLarge,
                color = Color(0xFFEAFEF5),
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = "A mobile-first security toolkit for networking, reconnaissance, Wi-Fi inspection and reporting.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFFB9E0D0)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
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
            .height(72.dp)
            .background(Color(0xFF173E31), RoundedCornerShape(16.dp)),
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
                color = Color(0xFFB9E0D0)
            )
        }
    }
}

@Composable
private fun ToolCardItem(tool: ToolCard) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { },
        colors = CardDefaults.cardColors(containerColor = Color(0xFF122A20)),
        shape = RoundedCornerShape(18.dp),
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
                    color = Color(0xFF1D8C67),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text(
                        text = tool.status,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                        color = Color(0xFFEBFFF5),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Text(
                text = tool.description,
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFFB9E0D0)
            )
        }
    }
}

@Composable
private fun NetworkContent() {
    val scanItems = listOf(
        "192.168.0.1 - Router",
        "192.168.0.2 - Mobile Device",
        "192.168.0.10 - Printer",
        "192.168.0.25 - Security Camera"
    )

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        SectionCard(title = "Network Overview") {
            scanItems.forEach { item ->
                Text(
                    text = item,
                    color = Color(0xFFB9E0D0),
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }

        SectionCard(title = "Port Scan Summary") {
            Text("Open ports: 22, 80, 443, 8080", color = Color(0xFF9BF5C8))
            Text("Status: One host reachable", color = Color(0xFFB9E0D0))
        }
    }
}

@Composable
private fun WifiContent() {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        SectionCard(title = "Current Wi-Fi") {
            Text("SSID: KaliLabNet", color = Color(0xFFB9E0D0))
            Text("Signal: Strong", color = Color(0xFF9BF5C8))
            Text("Channel: 6", color = Color(0xFFB9E0D0))
        }

        SectionCard(title = "Nearby Networks") {
            Text("NET-01 - -62 dBm", color = Color(0xFFB9E0D0))
            Text("Kali Guest - -71 dBm", color = Color(0xFFB9E0D0))
            Text("Office WiFi - -80 dBm", color = Color(0xFFB9E0D0))
        }
    }
}

@Composable
private fun ReportsContent() {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        SectionCard(title = "Session Logs") {
            Text("Scan started at 09:10", color = Color(0xFFB9E0D0))
            Text("DNS check completed", color = Color(0xFFB9E0D0))
            Text("Report exported successfully", color = Color(0xFF9BF5C8))
        }

        SectionCard(title = "Findings") {
            Text("1. Security camera reachable on LAN", color = Color(0xFFB9E0D0))
            Text("2. Wi-Fi signal stable and encrypted", color = Color(0xFFB9E0D0))
            Text("3. Vulnerability report prepared", color = Color(0xFF9BF5C8))
        }
    }
}

@Composable
private fun SectionCard(title: String, content: @Composable () -> Unit) {
    Surface(
        shape = RoundedCornerShape(18.dp),
        color = Color(0xFF122A20),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = Color(0xFFEAFEF5),
                fontWeight = FontWeight.Bold
            )
            content()
        }
    }
}

