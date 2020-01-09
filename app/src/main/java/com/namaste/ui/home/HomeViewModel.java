package com.namaste.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Central Council for Research in Siddha (CCRS) is the apex body pertaining to research in Siddha system of Medicine. CCRS is working towards the scientific validation of Siddha System of Medicine through clinical research, drug research, medicinal plants research, fundamental research, literary research and documentation. CCRS is carrying out its research activities and health care services mainly through 8 peripheral Institutes/Units – 3 located in Tamil Nadu, one each in Puducherry, Kerala, Karnataka, Andhra Pradesh and New Delhi. CCRS is currently carrying out 33 Intra-mural Research (IMR) Projects funded by Ministry of AYUSH. CCRS has more than 1000 palm leaf manuscripts and involved in decoding the formulations and other contents and publishing as books. CCRS is also publishing rare Siddha literatures, palm leaf manuscripts and till now 50 books have been published. The research outcomes are converted into intellectual property rights through patents and publications in peer reviewed journals.For the development of Siddha system of Medicine, Govt. of India, by bifurcating the erstwhile CCRAS, formed CCRS (Central Council for Research in Siddha) with its headquarters in Chennai and eight Research Institutes Units in six states namely, Tamil Nadu (Chennai, Mettur, Palayamkottai), Puducherry, New Delhi, Kerala (Thiruvananthapuram), Karnataka(Banglore) and Andra Pradesh (Tirupati). Siddha is a science of holistic health emphasizing both drug and diet for human health care. The Council has the vision of preservation and transmission of Knowledge and enhancement of the quality of research for developing drugs with quality, safety and efficacy through well-established preclinical and clinical research facilities — to prevent / manage /cure the diseases of varied aetiology. To undertake scientific research works in Siddha in a time-bound and cost-effective manner, to coordinate, aid, promote and collaborate research with different units of sister Councils and Research Organizations. To publish research articles/research journals, to exhibit achievements and to propagate research outcomes for all the stakeholders. To provide consultancy services for research projects and drug development (adopting both classical and modern techniques/equipments for Diagnosis, evolving evidence based Siddha drug treatment/therapy and promoting Siddha drug manufacture in collaboration with the other technical organizations).\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    public LiveData<String> getText() {
        return mText;
    }
}