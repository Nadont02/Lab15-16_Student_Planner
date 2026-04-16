package com.example.lab15_16_student_planner.ui_model

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.lab15_16_student_planner.data.sampleSubjects

data class ScheduleDay(val dayOfWeek: String, val subjects: List<ScheduleItem>)
data class ScheduleItem(val time: String, val subjectId: String)

val sampleSchedule = listOf(
    ScheduleDay("Понедельник", listOf(
        ScheduleItem("08:30 - 10:00", "1"), // Разработка мобильных приложений
        ScheduleItem("10:10 - 11:40", "3")  // Системное программирование
    )),
    ScheduleDay("Вторник", listOf(
        ScheduleItem("12:00 - 13:30", "2"),
        ScheduleItem("13:40 - 15:10", "4")
    )),
    ScheduleDay("Среда", listOf(
        ScheduleItem("08:30 - 10:00", "5")
    )),
    ScheduleDay("Четверг", listOf(
        ScheduleItem("10:10 - 11:40", "6"),
        ScheduleItem("12:00 - 13:30", "1")
    )),
    ScheduleDay("Пятница", listOf(
        ScheduleItem("13:40 - 15:10", "3")
    ))
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RaspisScreen(
    onNavigateBack: () -> Unit,
    onSubjectClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Расписание занятий") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Назад"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item { Spacer(modifier = Modifier.height(8.dp)) }

            items(sampleSchedule) { day ->
                Text(
                    text = day.dayOfWeek,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                day.subjects.forEach { scheduleItem ->
                    val subject = sampleSubjects.find { it.id == scheduleItem.subjectId }

                    if (subject != null) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                                .clickable { onSubjectClick(subject.id) }, // Навигация к деталям
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(16.dp),
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                Text(
                                    text = scheduleItem.time,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.secondary
                                )
                                Column {
                                    Text(
                                        text = subject.name,
                                        style = MaterialTheme.typography.bodyLarge,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = subject.professor,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}